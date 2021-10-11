package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.CarDamageInformationService;
import com.etiya.recap.business.constants.messages.CarDamageInfoMessages;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.CarDamageInformationDao;
import com.etiya.recap.dataAccess.abstracts.CarDao;
import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.concretes.CarDamageInformation;
import com.etiya.recap.entities.dtos.CarDamageInformationDto;
import com.etiya.recap.entities.requests.carDamageInfoRequests.CreateCarDamageInfoRequest;
import com.etiya.recap.entities.requests.carDamageInfoRequests.DeleteCarDamageInfoRequest;
import com.etiya.recap.entities.requests.carDamageInfoRequests.UpdateCarDamageInformationRequest;

@Service
public class CarDamageInformationManager implements CarDamageInformationService{

	private CarDamageInformationDao carDamageInformationDao;
	private CarDao carDao;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CarDamageInformationManager(CarDamageInformationDao carDamageInformationDao,CarDao carDao,ModelMapper modelMapper) {
		this.carDamageInformationDao = carDamageInformationDao;
		this.carDao = carDao;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public DataResult<List<CarDamageInformationDto>> getAll() {
		List<CarDamageInformation> carDamageInformations = this.carDamageInformationDao.findAll();
		List<CarDamageInformationDto> carDamageInformationDtos = carDamageInformations.stream().map(carinfo -> modelMapper.map(carinfo, CarDamageInformationDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDamageInformationDto>>(carDamageInformationDtos, CarDamageInfoMessages.GetAll);
	}

	@Override
	public DataResult<CarDamageInformationDto> getById(int id) {
		CarDamageInformation carDamageInformation = this.carDamageInformationDao.getById(id);
		CarDamageInformationDto carDamageInformationDto = modelMapper.map(carDamageInformation, CarDamageInformationDto.class);
		return new SuccessDataResult<CarDamageInformationDto>(carDamageInformationDto, CarDamageInfoMessages.GetById);
	}

	@Override
	public Result add(CreateCarDamageInfoRequest createCarDamageInfoRequest) {
		CarDamageInformation carDamageInformation = new CarDamageInformation();
		Car car = this.carDao.getById(createCarDamageInfoRequest.getCarId());
		carDamageInformation.setCar(car);
		carDamageInformation.setDamagePrice(createCarDamageInfoRequest.getDamagePrice());
		carDamageInformation.setDescription(createCarDamageInfoRequest.getDescription());
		
		this.carDamageInformationDao.save(carDamageInformation);
		return new SuccessResult(true, CarDamageInfoMessages.Add);
	}

	@Override
	public Result delete(DeleteCarDamageInfoRequest deleteCarDamageInfoRequest) {
		CarDamageInformation carDamageInformation = new CarDamageInformation();
		carDamageInformation.setId(deleteCarDamageInfoRequest.getId());
		
		this.carDamageInformationDao.delete(carDamageInformation);
		return new SuccessResult(true, CarDamageInfoMessages.Delete);
	}

	@Override
	public Result update(UpdateCarDamageInformationRequest updateCarDamageInformationRequest) {
		CarDamageInformation carDamageInformation = this.carDamageInformationDao.getById(updateCarDamageInformationRequest.getId());
		Car car = this.carDao.getById(updateCarDamageInformationRequest.getCarId());
		carDamageInformation.setCar(car);
		carDamageInformation.setDamagePrice(updateCarDamageInformationRequest.getDamagePrice());
		carDamageInformation.setDescription(updateCarDamageInformationRequest.getDescription());
		carDamageInformation.setId(updateCarDamageInformationRequest.getId());
		
		this.carDamageInformationDao.save(carDamageInformation);
		return new SuccessResult(true, CarDamageInfoMessages.Update);
	}

	@Override
	public DataResult<List<CarDamageInformationDto>> getCarDamageInfoByCarId(int carId) {
		List<CarDamageInformation> carDamageInformations = this.carDamageInformationDao.getByCar_id(carId);
		List<CarDamageInformationDto> carDamageInformationDtos = carDamageInformations.stream().map(cardamageinfo -> modelMapper.map(cardamageinfo, CarDamageInformationDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDamageInformationDto>>(carDamageInformationDtos, CarDamageInfoMessages.GetCarDamageInfoByCarId);
	}

}
