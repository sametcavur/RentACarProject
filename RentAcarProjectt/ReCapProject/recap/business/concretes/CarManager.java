package com.etiya.recap.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.CarService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.CarDao;
import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.dtos.CarDetailDto;


@Service
public class CarManager implements CarService {

	private CarDao carDao;

	@Autowired
	public CarManager(CarDao carDao) {
		this.carDao = carDao;
	}

	@Override
	public DataResult<List<Car>> getAll() {
		return new SuccessDataResult<List<Car>>(this.carDao.findAll(), "Arabalar başarıyla listelendi");
	}

	@Override
	public Result add(Car car) {
		this.carDao.save(car);
		return new SuccessResult(true, "Araba başarıyla eklendi");

	}

	@Override
	public DataResult<Car> getById(int id) {
		return new SuccessDataResult<Car>(this.carDao.getById(id), "Araba listelendi");
	}

	@Override
	public Result delete(Car car) {
		this.carDao.delete(car);
		return new SuccessResult(true, "Araba silindi");

	}

	@Override
	public Result update(Car car) {
		this.carDao.save(car);
		return new SuccessResult(true, "Araba güncellendi");
	}

	@Override
	public DataResult<List<CarDetailDto>> getAllCarsWithDetail() {
		return new SuccessDataResult<List<CarDetailDto>>(this.carDao.getCarWithDetails(), "Arabalar başarıyla listelendi");
	}

}
