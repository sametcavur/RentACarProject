package com.etiya.recap.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etiya.recap.business.abstracts.CorporateInvoicesService;
import com.etiya.recap.business.abstracts.CreditCardService;
import com.etiya.recap.business.abstracts.CustomerFindeksScoreCheckService;
import com.etiya.recap.business.abstracts.IndividualInvoicesService;
import com.etiya.recap.business.abstracts.RentalService;
import com.etiya.recap.business.constants.messages.RentalMessages;
import com.etiya.recap.core.utilities.business.BusinessRules;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.ErrorResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.AdditionalServicesDao;
import com.etiya.recap.dataAccess.abstracts.CarDao;
import com.etiya.recap.dataAccess.abstracts.CityDao;
import com.etiya.recap.dataAccess.abstracts.CreditCardDao;
import com.etiya.recap.dataAccess.abstracts.RentalDao;
import com.etiya.recap.entities.concretes.AdditionalServices;
import com.etiya.recap.entities.concretes.ApplicationUser;
import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.concretes.City;
import com.etiya.recap.entities.concretes.CorporateCustomer;
import com.etiya.recap.entities.concretes.CreditCard;
import com.etiya.recap.entities.concretes.IndividualCustomer;
import com.etiya.recap.entities.concretes.Rental;
import com.etiya.recap.entities.dtos.RentalDto;
import com.etiya.recap.entities.requests.invoiceRequests.CreateInvoicesRequest;
import com.etiya.recap.entities.requests.rentalRequests.CreateDeliverTheCar;
import com.etiya.recap.entities.requests.rentalRequests.CreateRentalRequest;
import com.etiya.recap.entities.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.recap.entities.requests.rentalRequests.UpdateRentalRequest;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private CarDao carDao;
	private CreditCardDao creditCardDao;
	private AdditionalServicesDao additionalServicesDao;
	private CityDao cityDao;
	private CustomerFindeksScoreCheckService customerFindeksScoreCheckService;
	private CreditCardService creditCardService;
	private CorporateInvoicesService corporateInvoicesService;
	private IndividualInvoicesService individualInvoicesService;
	private ModelMapper modelMapper;

	@Autowired
	public RentalManager(RentalDao rentalDao, CarDao carDao,
			CustomerFindeksScoreCheckService customerFindeksScoreCheckService,CreditCardDao creditCardDao,
			CorporateInvoicesService corporateInvoicesService,IndividualInvoicesService individualInvoicesService
			,AdditionalServicesDao additionalServicesDao,CreditCardService creditCardService,ModelMapper modelMapper,
			CityDao cityDao) {
		this.rentalDao = rentalDao;
		this.carDao = carDao;
		this.customerFindeksScoreCheckService = customerFindeksScoreCheckService;
		this.creditCardDao = creditCardDao;
		this.corporateInvoicesService = corporateInvoicesService;
		this.individualInvoicesService = individualInvoicesService;
		this.additionalServicesDao = additionalServicesDao;
		this.creditCardService = creditCardService;
		this.cityDao = cityDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<RentalDto>> getAll() {
		List<Rental> rentals = this.rentalDao.findAll();
		List<RentalDto> rentalsDtos = rentals.stream().map(rental -> modelMapper.map(rental, RentalDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<>(rentalsDtos, RentalMessages.GetAll);
	}

	@Transactional
	@Override
	public Result rentCorporateCustomer(CreateRentalRequest createRentalRequest){
		Car car =this.carDao.getById(createRentalRequest.getCarId());

		City city = this.cityDao.getById(createRentalRequest.getReturnCityId());

		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(createRentalRequest.getUserId());

		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setApplicationUser(applicationUser);

		Rental rental = new Rental();
     	rental.setRentDate(createRentalRequest.getRentDate());
	    rental.setReturnDate(createRentalRequest.getReturnDate());
	    rental.setKilometer(createRentalRequest.getKilometer());
		rental.setCar(car);
		rental.setUser(applicationUser);
		rental.setReturnCity(city);

		CreditCard creditCard = this.setGetCreditCard(createRentalRequest);
		creditCard.setApplicationUser(applicationUser);

		//Kiralama g??n say??s?? ve total fiyat de??i??kenleri
		long totalRentDateCount=this.calculatetotalRentDateCount(rental);
		double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);

		//???? motoru
		var result = BusinessRules.run(checkCarIsReturned(car.getId()),checkCorporateCustomerFindeksScore(corporateCustomer, car)
		,creditCardService.checkCreditCardNumber(creditCard),creditCardService.checkCreditCardLimit(creditCard, rentPrice, rental)
		,this.checkIsCarInCare(car));

		if (result != null) {

			return result;
		}

		//Araban??n kiralanmadan ??nceki ili kiralanma ??ehri olacak
		rental.setRentalStartingCity(car.getCity());

		//Arabay?? tekrar teslim etti??imizde teslim ili araban??n bulundu??u il olacak
		car.setCity(city);

		//M????terinin girdi??i km bilgisini araban??n km bilgisi ile g??ncelleme
		car.setKilometer(createRentalRequest.getKilometer()+car.getKilometer());

		//Araban??n m??saitlik durumunu false yap
		car.setCarIsAvailable(false);

		//E??er m????teri kredi kart??n?? kaydetmek istiyorsa kaydetme i??lemi
		if(createRentalRequest.isSaveCreditCard()) {
			this.creditCardDao.save(creditCard);
		}

		//createRentalRequest i??erisindeki additionalServiceIDlerini rental i??indeki additionalServislere atma i??lemi
		List<AdditionalServices> additionalServices = this.transferAdditionalServices(createRentalRequest);
		rental.setAdditionalServices(additionalServices);

		//Ek Hizmetleri Hesaplama Metodu
		rentPrice = this.calculatePrice(rental, rentPrice);

		this.rentalDao.save(rental);
		//E??er araban??n teslim g??n?? belli ise fatura an??nda kesilecek
		//Bunu save'den sonra yapmam??z??n sebebi,rental kay??t olduktan sonra IDsini get edebiliyoruz.
		if(rental.getReturnDate()!=null){
		//araban??n teslim tarihi belli ise m??saitlik durumunu true yap
		car.setCarIsAvailable(true);
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.corporateInvoicesService.add(createInvoicesRequest);
		}
		return new SuccessResult(true, RentalMessages.Add);
	}

	@Override
	public Result rentIndividualCustomer(CreateRentalRequest createRentalRequest) {

		Car car =this.carDao.getById(createRentalRequest.getCarId());

		City city = this.cityDao.getById(createRentalRequest.getReturnCityId());

		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(createRentalRequest.getUserId());

		IndividualCustomer individualCustomer = new IndividualCustomer();
		individualCustomer.setApplicationUser(applicationUser);

		Rental rental = new Rental();
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setUser(applicationUser);
		rental.setReturnCity(city);
		rental.setKilometer(createRentalRequest.getKilometer());

		CreditCard creditCard = this.setGetCreditCard(createRentalRequest);
		creditCard.setApplicationUser(applicationUser);

		//Kiralama g??n say??s?? ve total fiyat de??i??kenleri
		long totalRentDateCount=this.calculatetotalRentDateCount(rental);
		double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);

		//???? motoru
		var result = BusinessRules.run(checkCarIsReturned(car.getId()),checkIndividualCustomerFindeksScore(individualCustomer, car)
		,creditCardService.checkCreditCardNumber(creditCard),creditCardService.checkCreditCardLimit(creditCard, rentPrice, rental)
		,this.checkIsCarInCare(car));

		if (result != null) {
				return result;
		}

		//Ara?? bak??mda ise kiralanamaz
		if(!car.isCarIsAvailable()) {
			return new ErrorResult(RentalMessages.ErrorIfCarIsNotAvailableToRent);
		}

		//Araban??n kiralanmadan ??nceki ili kiralanma ??ehri olacak
		rental.setRentalStartingCity(car.getCity());

		//Arabay?? tekrar teslim etti??imizde teslim ili araban??n bulundu??u il olacak
		car.setCity(city);

		//M????terinin girdi??i km bilgisini araban??n km bilgisi ile g??ncelleme
		car.setKilometer(createRentalRequest.getKilometer()+car.getKilometer());

		//Araban??n m??saitlik durumunu false yap
		car.setCarIsAvailable(false);

		//E??er m????teri kredi kart??n?? kaydetmek istiyorsa kaydetme i??lemi
		if(createRentalRequest.isSaveCreditCard()) {
			this.creditCardDao.save(creditCard);
		}

		//createRentalRequest i??erisindeki additionalServiceIDlerini rental i??indeki additionalServislere atma i??lemi
		List<AdditionalServices> additionalServices = this.transferAdditionalServices(createRentalRequest);
		rental.setAdditionalServices(additionalServices);

		//Ek Hizmetleri Hesaplama Metodu
		rentPrice = this.calculatePrice(rental, rentPrice);


		this.rentalDao.save(rental);
		//E??er araban??n teslim g??n?? belli ise fatura an??nda kesilecek
		//Bunu save'den sonra yapmam??z??n sebebi,rental kay??t olduktan sonra IDsini get edebiliyoruz.
		if(rental.getReturnDate()!=null){
		//araban??n teslim tarihi belli ise m??saitlik durumunu true yap
		car.setCarIsAvailable(true);
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.individualInvoicesService.add(createInvoicesRequest);
		}
		return new SuccessResult(true, RentalMessages.Add);
	}

	@Override
	public DataResult<RentalDto> getById(int id) {
		Rental rentals = this.rentalDao.getById(id);
		RentalDto rentalDto = modelMapper.map(rentals, RentalDto.class);

		return new SuccessDataResult<>(rentalDto, RentalMessages.GetById);
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = new Rental();
		rental.setId(deleteRentalRequest.getId());

		this.rentalDao.delete(rental);
		return new SuccessResult(true, RentalMessages.Delete);
	}

	@Override
	public Result updateIndividualCustomerRent(UpdateRentalRequest updateRentalRequest) {

		Car car =this.carDao.getById(updateRentalRequest.getCarId());

		City city = this.cityDao.getById(updateRentalRequest.getReturnCityId());

		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(updateRentalRequest.getUserId());

		IndividualCustomer individualCustomer = new IndividualCustomer();
		individualCustomer.setApplicationUser(applicationUser);

		Rental rental = this.rentalDao.getById(updateRentalRequest.getId());
		rental.setRentDate(updateRentalRequest.getRentDate());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setUser(applicationUser);
		rental.setReturnCity(city);
		rental.setKilometer(updateRentalRequest.getKilometer());


		//Kiralama g??n say??s?? ve total fiyat de??i??kenleri
		long totalRentDateCount=this.calculatetotalRentDateCount(rental);
		double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);

		//???? motoru
		var result = BusinessRules.run(checkCarIsReturned(car.getId()),checkIndividualCustomerFindeksScore(individualCustomer, car));
		if (result != null) {
			return result;
		}

		//Ara?? bak??mda ise kiralanamaz
		if(!car.isCarIsAvailable()) {
			return new ErrorResult(RentalMessages.ErrorIfCarIsNotAvailableToRent);
		}

		//Araban??n kiralanmadan ??nceki ili kiralanma ??ehri olacak
		rental.setRentalStartingCity(car.getCity());

		//Arabay?? tekrar teslim etti??imizde teslim ili araban??n bulundu??u il olacak
		car.setCity(city);

		//M????terinin girdi??i km bilgisini araban??n km bilgisi ile g??ncelleme
		car.setKilometer(updateRentalRequest.getKilometer()+car.getKilometer());

		//createRentalRequest i??erisindeki additionalServiceIDlerini rental i??indeki additionalServislere atma i??lemi
		List<AdditionalServices> additionalServices = new ArrayList<>();
		for (Integer additionalServiceId : updateRentalRequest.getAdditionalServicesId()) {
			additionalServices.add(this.additionalServicesDao.getById(additionalServiceId));
		}
		rental.setAdditionalServices(additionalServices);
		//*************************************************************************************************************

		//Ek Hizmetleri Hesaplama Metodu
		rentPrice = this.calculatePrice(rental, rentPrice);

		this.rentalDao.save(rental);
		//E??er araban??n teslim g??n?? belli ise fatura an??nda kesilecek
		if(rental.getReturnDate()!=null)
		{

		//Bunu save'den sonra yapmam??z??n sebebi,rental kay??t olduktan sonra IDsini get edebiliyoruz.
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.individualInvoicesService.add(createInvoicesRequest);

		}

		return new SuccessResult(true, RentalMessages.Update);
	}

	@Override
	public Result updateCorporateCustomerRent(UpdateRentalRequest updateRentalRequest) {

		Car car =this.carDao.getById(updateRentalRequest.getCarId());

		City city = this.cityDao.getById(updateRentalRequest.getReturnCityId());

		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(updateRentalRequest.getUserId());

		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setApplicationUser(applicationUser);

		Rental rental = this.rentalDao.getById(updateRentalRequest.getId());
		rental.setRentDate(updateRentalRequest.getRentDate());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setUser(applicationUser);
		rental.setReturnCity(city);
		rental.setKilometer(updateRentalRequest.getKilometer());

		//Kiralama g??n say??s?? ve total fiyat de??i??kenleri
		long totalRentDateCount=this.calculatetotalRentDateCount(rental);
		double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);

		//???? motoru
		var result = BusinessRules.run(checkCarIsReturned(car.getId()),checkCorporateCustomerFindeksScore(corporateCustomer, car));
		if (result != null) {
			return result;
		}

		//Ara?? bak??mda ise kiralanamaz
		if(!car.isCarIsAvailable()) {
			return new ErrorResult(RentalMessages.ErrorIfCarIsNotAvailableToRent);
		}

		//Araban??n kiralanmadan ??nceki ili kiralanma ??ehri olacak
		rental.setRentalStartingCity(car.getCity());

		//Arabay?? tekrar teslim etti??imizde teslim ili araban??n bulundu??u il olacak
		car.setCity(city);

		//M????terinin girdi??i km bilgisini araban??n km bilgisi ile g??ncelleme
		car.setKilometer(updateRentalRequest.getKilometer()+car.getKilometer());


		//createRentalRequest i??erisindeki additionalServiceIDlerini rental i??indeki additionalServislere atma i??lemi
		List<AdditionalServices> additionalServices = new ArrayList<>();
		for (Integer additionalServiceId : updateRentalRequest.getAdditionalServicesId()) {
			additionalServices.add(this.additionalServicesDao.getById(additionalServiceId));
		}
		rental.setAdditionalServices(additionalServices);
		//*************************************************************************************************************

		//Ek Hizmetleri Hesaplama Metodu
		rentPrice = this.calculatePrice(rental, rentPrice);

		this.rentalDao.save(rental);
		//E??er araban??n teslim g??n?? belli ise fatura an??nda kesilecek
		if(rental.getReturnDate()!=null)
		{

		//Bunu save'den sonra yapmam??z??n sebebi,rental kay??t olduktan sonra IDsini get edebiliyoruz.
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.corporateInvoicesService.add(createInvoicesRequest);

		}

		return new SuccessResult(true, RentalMessages.Update);
	}

	//Kurumsal m????terinin arabas??n?? teslim ettir
	@Override
	public Result deliverCorporateCustomerCar(CreateDeliverTheCar createDeliverTheCar) {

		Rental rental=this.rentalDao.getById(createDeliverTheCar.getRentalId());
		rental.setReturnDate(createDeliverTheCar.getReturnDate());

		//Araba teslim edildikten sonra m??saitlik durumunu true yap
		Car car = rental.getCar();
		car.setCarIsAvailable(true);


		//Kiralama g??n say??s?? ve total fiyat de??i??kenleri
		long totalRentDateCount=this.calculatetotalRentDateCount(rental);
		double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);

	   	rentPrice = this.calculatePrice(rental, rentPrice);

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.corporateInvoicesService.add(createInvoicesRequest);

		return new SuccessResult(true,RentalMessages.DeliverCar);

	}

	//Bireysel m????terinin arabas??n?? teslim ettir
	@Override
	public Result deliverIndividualCustomerCar(CreateDeliverTheCar createDeliverTheCar) {

       Rental rental=this.rentalDao.getById(createDeliverTheCar.getRentalId());
   	   rental.setReturnDate(createDeliverTheCar.getReturnDate());

   	   //Araba teslim edildikten sonra m??saitlik durumunu true yap
   	   Car car = rental.getCar();
   	   car.setCarIsAvailable(true);

   	   //Kiralama g??n say??s?? ve total fiyat de??i??kenleri
   		long totalRentDateCount=this.calculatetotalRentDateCount(rental);
   		double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);

   	   rentPrice = this.calculatePrice(rental, rentPrice);

		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.individualInvoicesService.add(createInvoicesRequest);

		return new SuccessResult(true,RentalMessages.DeliverCar);
	}

	//araban??n return datesi null ise araba m??sait de??ildir
	private Result checkCarIsReturned(int carId) {
		Rental rental = this.rentalDao.getByCar_idAndReturnDateIsNull(carId);
		if(rental!=null) {
			return new ErrorResult(RentalMessages.ErrorIfCarIsNotAvailable);
		}else {
			return new SuccessResult();
		}
	}

	//Bireysel m????terinin kiralama i??in findeks skoru yeterli mi?
	private Result checkIndividualCustomerFindeksScore(IndividualCustomer individualCustomer,Car car) {
		int individualCustomerFindeksScore = this.customerFindeksScoreCheckService.checkIndividualCustomerFindeksScore(individualCustomer);
		if(car.getFindeksScore()>=individualCustomerFindeksScore) {
			return new ErrorResult(RentalMessages.ErrorFindeksScore);
		}
		return new SuccessResult();
	}

	//Kurumsal m????terinin kiralama i??in findeks skoru yeterli mi?
	private Result checkCorporateCustomerFindeksScore(CorporateCustomer corporateCustomer, Car car) {
		int corporateCustomerFindeksScore = this.customerFindeksScoreCheckService.checkCorporateCustomerFindeksScore(corporateCustomer);
		if(car.getFindeksScore()>=corporateCustomerFindeksScore) {
			return new ErrorResult(RentalMessages.ErrorFindeksScore);
		}
		return new SuccessResult();
		}

	//Hizmet bedelleri hesaplama
	private double calculatePrice(Rental rental,double rentPrice) {
		//Ara?? ba??lang???? ??ehrine teslim edilmediyse 500 tl fark al??nacak
		if(!rental.getReturnCity().equals(rental.getRentalStartingCity()))
			{
			rentPrice+=500;
			}
		for (AdditionalServices additionalService : rental.getAdditionalServices()) {
			rentPrice+=additionalService.getAdditionalServicePrice();
			}
		return rentPrice;
		}

	//Kiralama g??n say??s?? ve total fiyat de??i??kenleri
	private long calculatetotalRentDateCount(Rental rental) {
		long totalRentDateCount=0;
		if(rental.getReturnDate()!=null) {
		totalRentDateCount = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),rental.getReturnDate().toInstant());
		}
		return totalRentDateCount;
	}

	public CreditCard setGetCreditCard(CreateRentalRequest createRentalRequest) {
		CreditCard creditCard = new CreditCard();
		creditCard.setCardNumber(createRentalRequest.getCreditCardDto().getCardNumber());
		creditCard.setCvc(createRentalRequest.getCreditCardDto().getCvc());
		creditCard.setExpirationDate(createRentalRequest.getCreditCardDto().getExpirationDate());
		creditCard.setNameOnTheCard(createRentalRequest.getCreditCardDto().getNameOnTheCard());
		return creditCard;
	}

	//Ara?? bak??mda ise kiralanamaz
	private Result checkIsCarInCare(Car car) {
		if(!car.isCarIsAvailable()) {
			return new ErrorResult(RentalMessages.ErrorIfCarIsNotAvailableToRent);
		}
		return new SuccessResult();
	}

	//createRentalRequest i??erisindeki additionalServiceIDlerini rental i??indeki additionalServislere atma i??lemi
	public List<AdditionalServices> transferAdditionalServices(CreateRentalRequest createRentalRequest){
		List<AdditionalServices> additionalServices = new ArrayList<>();
		for (Integer additionalServiceId : createRentalRequest.getAdditionalServicesId()) {
			additionalServices.add(this.additionalServicesDao.getById(additionalServiceId));
		}
		return additionalServices;
	}


}
