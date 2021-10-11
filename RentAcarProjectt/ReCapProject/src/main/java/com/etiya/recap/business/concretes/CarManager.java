package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.CarService;
import com.etiya.recap.business.constants.messages.CarMessages;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.CarDao;
import com.etiya.recap.entities.concretes.Brand;
import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.concretes.City;
import com.etiya.recap.entities.concretes.Color;
import com.etiya.recap.entities.dtos.CarDetailDto;
import com.etiya.recap.entities.dtos.CarDetailWithCarImgDto;
import com.etiya.recap.entities.requests.carRequests.CreateCarRequest;
import com.etiya.recap.entities.requests.carRequests.DeleteCarRequest;
import com.etiya.recap.entities.requests.carRequests.UpdateCarAvailableRequest;
import com.etiya.recap.entities.requests.carRequests.UpdateCarRequest;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private final ModelMapper modelMapper;

	@Autowired
	public CarManager(CarDao carDao,ModelMapper modelMapper) {
		this.carDao = carDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<CarDetailDto>> getAll() {
		List<Car> carDetails = this.carDao.findAll();
		List<CarDetailDto> carDetailDtos = carDetails.stream().map(carDetail -> modelMapper.map(carDetail, CarDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos, CarMessages.GetAll);
	}
	
	@Override
	public DataResult<List<CarDetailDto>> getCarsByCityId(int cityId) {
		List<Car> carDetails = this.carDao.getByCity_cityId(cityId);
		List<CarDetailDto> carDetailDtos = carDetails.stream().map(carDetail -> modelMapper.map(carDetail, CarDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos, CarMessages.GetCarsByCityId);
	}

	@Override
	public DataResult<List<CarDetailDto>> getAllCarsInCare() {
		List<Car> carDetails = this.carDao.getByCarIsAvailableIsFalse();
		List<CarDetailDto> carDetailDtos = carDetails.stream().map(carDetail -> modelMapper.map(carDetail, CarDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos, CarMessages.GetCarsInCare);
	}

	@Override
	public DataResult<List<CarDetailDto>> getAllCarsNotInCare() {
		List<Car> carDetails = this.carDao.getByCarIsAvailableIsTrue();
		List<CarDetailDto> carDetailDtos = carDetails.stream().map(carDetail -> modelMapper.map(carDetail, CarDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos, CarMessages.GetCarsAreNotInCare);
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Brand brand = new Brand();
		brand.setBrandId(createCarRequest.getBrandId());

		Color color = new Color();
		color.setColorId(createCarRequest.getColorId());
		
		City city = new City();
		city.setCityId(createCarRequest.getCityId());

		Car car = new Car();
		car.setModelYear(createCarRequest.getModelYear());
		car.setDailyPrice(createCarRequest.getDailyPrice());
		car.setDescription(createCarRequest.getDescription());
		car.setFindeksScore(createCarRequest.getFindeksScore());
		car.setCity(city);
		car.setKilometer(createCarRequest.getKilometer());
		car.setBrand(brand);
		car.setColor(color);
		
		this.carDao.save(car);
     	return new SuccessResult(true, CarMessages.Add);
	}

	@Override
	public DataResult<CarDetailDto> getById(int id) {
		Car car = this.carDao.getById(id);
		CarDetailDto carDetailDto = modelMapper.map(car, CarDetailDto.class);
		return new SuccessDataResult<CarDetailDto>(carDetailDto, CarMessages.GetById);
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		Car car = new Car();
		car.setId(deleteCarRequest.getId());

		this.carDao.delete(car);
		return new SuccessResult(true, CarMessages.Delete);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {

		Brand brand = new Brand();
		brand.setBrandId(updateCarRequest.getBrandId());

		Color color = new Color();
		color.setColorId(updateCarRequest.getColorId());
		
		City city = new City();
		city.setCityId(updateCarRequest.getCityId());

		Car car = this.carDao.getById(updateCarRequest.getId());
		car.setId(updateCarRequest.getId());
		car.setModelYear(updateCarRequest.getModelYear());
		car.setDailyPrice(updateCarRequest.getDailyPrice());
		car.setDescription(updateCarRequest.getDescription());
		car.setFindeksScore(updateCarRequest.getFindeksScore());
		car.setKilometer(updateCarRequest.getKilometer());
		car.setCarIsAvailable(updateCarRequest.isCarIsAvailable());
		car.setCity(city);
		car.setBrand(brand);
		car.setColor(color);

		this.carDao.save(car);
		return new SuccessResult(true, CarMessages.Update);
		
	}
	
	@Override
	public Result updateCarAvailable(UpdateCarAvailableRequest updateCarAvailableRequest) {
		Car car = this.carDao.getById(updateCarAvailableRequest.getId());
		car.setCarIsAvailable(updateCarAvailableRequest.isCarIsAvailable());
		this.carDao.save(car);
		return new SuccessResult(true, CarMessages.ChangeCarSituation);
	}


	@Override
	public DataResult<List<CarDetailDto>> getCarByBrandId(int brandId) {
		List<Car> cars = this.carDao.getByBrand_brandId(brandId);
		List<CarDetailDto> carDetailDtos = cars.stream().map(carDetail -> modelMapper.map(carDetail, CarDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos, CarMessages.GetCarsByBrandId);
	}

	@Override
	public DataResult<List<CarDetailDto>> getCarByColorId(int colorId) {
		List<Car> cars = this.carDao.getByColor_colorId(colorId);
		List<CarDetailDto> carDetailDtos = cars.stream().map(carDetail -> modelMapper.map(carDetail, CarDetailDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarDetailDto>>(carDetailDtos, CarMessages.GetCarsByColorId);
	}

	@Override
	public DataResult<List<CarDetailWithCarImgDto>>  getCarWithCarImgByCarId(int id) {
		List<CarDetailWithCarImgDto> cars=this.carDao.getCarWithCarImg(id);
        return new SuccessDataResult<List<CarDetailWithCarImgDto>>(cars, CarMessages.GetCarsWithImages);
	}


}
	
