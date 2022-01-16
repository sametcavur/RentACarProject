package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.CreditCardService;
import com.etiya.recap.business.constants.messages.CreditCardMessages;
import com.etiya.recap.core.services.posService.PosService;
import com.etiya.recap.core.utilities.business.BusinessRules;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.ErrorResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.CreditCardDao;
import com.etiya.recap.entities.concretes.ApplicationUser;
import com.etiya.recap.entities.concretes.CreditCard;
import com.etiya.recap.entities.concretes.Rental;
import com.etiya.recap.entities.dtos.CreditCardDetailDto;
import com.etiya.recap.entities.requests.creditCardRequests.CreateCreditCardRequest;
import com.etiya.recap.entities.requests.creditCardRequests.CreatePosServiceRequest;
import com.etiya.recap.entities.requests.creditCardRequests.DeleteCreditCardRequest;
import com.etiya.recap.entities.requests.creditCardRequests.UpdateCreditCardRequest;


@Service
public class CreditCardManager implements CreditCardService {

	private CreditCardDao creditCardDao;
	private PosService posService;
	private  ModelMapper modelMapper;

	@Autowired
	public CreditCardManager(CreditCardDao creditCardDao,PosService posService,ModelMapper modelMapper) {
		this.creditCardDao = creditCardDao;
		this.posService = posService;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<CreditCardDetailDto>> getAll() {
		List<CreditCard> creditCards = this.creditCardDao.findAll();
		List<CreditCardDetailDto> creditCardDtos = creditCards.stream().map(creditCard -> modelMapper.map(creditCard, CreditCardDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<>(creditCardDtos, CreditCardMessages.GetAll);
	}

	@Override
	public Result add(CreateCreditCardRequest createCreditCardRequest) {

		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(createCreditCardRequest.getUserId());

		CreditCard creditCard=new CreditCard();
		creditCard.setNameOnTheCard(createCreditCardRequest.getNameOnTheCard());
		creditCard.setCardNumber(createCreditCardRequest.getCardNumber());
		creditCard.setExpirationDate(createCreditCardRequest.getExpirationDate());
		creditCard.setCvc(createCreditCardRequest.getCvc());

		creditCard.setApplicationUser(applicationUser);

		var result = BusinessRules.run(this.checkCreditCardNumber(creditCard));

		if (result != null) {
			return result;
		}


		this.creditCardDao.save(creditCard);
     	return new SuccessResult(true, CreditCardMessages.Add);
	}

	@Override
	public DataResult<CreditCardDetailDto> getById(int id) {
		CreditCard creditCard = this.creditCardDao.getById(id);
		CreditCardDetailDto creditCardDto = modelMapper.map(creditCard, CreditCardDetailDto.class);
		return new SuccessDataResult<>(creditCardDto, CreditCardMessages.GetById);
	}

	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {

		CreditCard creditCard=new CreditCard();
		creditCard.setId(deleteCreditCardRequest.getId());

		this.creditCardDao.delete(creditCard);
		return new  SuccessResult(true,CreditCardMessages.Delete);
	}

	@Override
	public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(updateCreditCardRequest.getUserId());

		CreditCard creditCard=this.creditCardDao.getById(updateCreditCardRequest.getId());
		creditCard.setId(updateCreditCardRequest.getId());
		creditCard.setNameOnTheCard(updateCreditCardRequest.getNameOnTheCard());
		creditCard.setCardNumber(updateCreditCardRequest.getCardNumber());
		creditCard.setExpirationDate(updateCreditCardRequest.getExpirationDate());
		creditCard.setCvc(updateCreditCardRequest.getCvc());

		creditCard.setApplicationUser(applicationUser);

		var result = BusinessRules.run(this.checkCreditCardNumber(creditCard));

		if (result != null) {
			return result;
		}

		this.creditCardDao.save(creditCard);
     	return new SuccessResult(true, CreditCardMessages.Add);
	}

	//Kredi kartı kontrolü
	@Override
	public Result checkCreditCardNumber(CreditCard creditCard) {
		String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
		        "(?<mastercard>5[1-5][0-9]{14})|" +
		        "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
		        "(?<amex>3[47][0-9]{13})|" +
		        "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
		        "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

		Pattern pattern = Pattern.compile(regex);
		creditCard.setCardNumber(creditCard.getCardNumber().replaceAll("-", ""));
		Matcher matcher = pattern.matcher(creditCard.getCardNumber());
		if(matcher.matches()) {
			return new SuccessResult();
		}else {
			return new ErrorResult(CreditCardMessages.ErrorIfCreditCardIsWrong);
		}
	}

	@Override
	public Result checkCreditCardLimit(CreditCard creditCard, double rentPrice, Rental rental) {
	//Kredi Kartı Limit Kontrolü********************************************************
	CreatePosServiceRequest createPosServiceRequest = new CreatePosServiceRequest();
	createPosServiceRequest.setCardNumber(creditCard.getCardNumber());
	createPosServiceRequest.setCvc(creditCard.getCvc());
	createPosServiceRequest.setExpirationDate(creditCard.getExpirationDate());
	createPosServiceRequest.setNameOnTheCard(creditCard.getNameOnTheCard());
	createPosServiceRequest.setFeePayable(rentPrice);

	//Eğer limit yetersiz ise rent işlemi gerçekleşmeyecek kontrolü
	if(this.posService.withdraw(createPosServiceRequest))
	{
		return new SuccessResult();
	}
	else {
		return new ErrorResult(CreditCardMessages.ErrorMoneyIsNotEnoughToRentACar);
	}
	//************************************************************************************
	}

}



