package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.CarCareService;
import com.etiya.recap.business.constants.messages.CarCareMessages;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.ErrorResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.CarCareDao;
import com.etiya.recap.dataAccess.abstracts.CarDao;
import com.etiya.recap.dataAccess.abstracts.RentalDao;
import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.concretes.CarCare;
import com.etiya.recap.entities.concretes.Rental;
import com.etiya.recap.entities.dtos.CarCareDto;
import com.etiya.recap.entities.requests.carCareRequests.CreateCarCareRequest;
import com.etiya.recap.entities.requests.carCareRequests.DeleteCarCareRequest;
import com.etiya.recap.entities.requests.carCareRequests.UpdateCarCareRequest;

@Service
public class CarCareManager implements CarCareService {

	private CarCareDao carCareDao;
	private RentalDao rentalDao;
	private CarDao carDao;
	private final ModelMapper modelMapper;

	@Autowired
	public CarCareManager(CarCareDao carCareDao,RentalDao rentalDao,CarDao carDao,ModelMapper modelMapper) {
		this.carCareDao = carCareDao;
		this.rentalDao = rentalDao;
		this.carDao = carDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<CarCareDto>> getAll() {
		List<CarCare> carCares = this.carCareDao.findAll();
		List<CarCareDto> carCareDtos = carCares.stream().map(carcare -> modelMapper.map(carcare, CarCareDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<>(carCareDtos, CarCareMessages.GetAllCarsInCare);
	}

	@Override
	public DataResult<CarCareDto> getById(int id) {
		CarCare carCare = this.carCareDao.getById(id);
		CarCareDto carCareDto = modelMapper.map(carCare, CarCareDto.class);
		return new SuccessDataResult<>(carCareDto, CarCareMessages.GetById);
	}

	@Override
	public Result sendCarToCare(CreateCarCareRequest createCarCareRequest) {
		//Araç müşteride ise bakıma gönderilemez***********************************
		Rental rental = this.rentalDao.getByCar_idAndReturnDateIsNull(createCarCareRequest.getCarId());
		if(rental!=null) {
			return new ErrorResult(CarCareMessages.ErrorIfCarIsNotAvailableToGoToCare);
		}
		//**************************************************************************

		Car car = this.carDao.getById(createCarCareRequest.getCarId());
		car.setCarIsAvailable(false);


		CarCare carCare = modelMapper.map(createCarCareRequest, CarCare.class);
		carCare.setCar(car);

		this.carCareDao.save(carCare);
		return new SuccessResult(true, CarCareMessages.SendCarToCare);
	}

	@Override
	public Result delete(DeleteCarCareRequest deleteCarCareRequest) {

		CarCare carCare = new CarCare();
		carCare.setId(deleteCarCareRequest.getId());

		this.carCareDao.delete(carCare);
		return new SuccessResult(true, CarCareMessages.Delete);
	}

	@Override
	public Result update(UpdateCarCareRequest updateCarCareRequest) {
	//Araç müşteride ise bakıma gönderilemez***********************************
	Rental rental = this.rentalDao.getByCar_idAndReturnDateIsNull(updateCarCareRequest.getCarId());
	if(rental!=null) {
		return new ErrorResult(CarCareMessages.ErrorIfCarIsNotAvailableToGoToCare);
	}
	//**************************************************************************

	Car car = this.carDao.getById(updateCarCareRequest.getCarId());
	car.setCarIsAvailable(false);

	CarCare carCare = this.carCareDao.getById(updateCarCareRequest.getId());
	carCare.setCar(car);
	carCare.setCarCareFinishDate(updateCarCareRequest.getCarCareFinishDate());
	carCare.setCarCareStartDate(updateCarCareRequest.getCarCareStartDate());
	carCare.setId(updateCarCareRequest.getId());
	this.carCareDao.save(carCare);
	return new SuccessResult(true, CarCareMessages.Update);
	}

}
