package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.dtos.CarDetailDto;


public interface CarService {

	DataResult<List<Car>> getAll();

	Result add(Car car);
	
	DataResult<Car> getById(int id);
	
	Result delete(Car car);
	
	Result update(Car car);
	
	DataResult<List<CarDetailDto>> getAllCarsWithDetail();
}
