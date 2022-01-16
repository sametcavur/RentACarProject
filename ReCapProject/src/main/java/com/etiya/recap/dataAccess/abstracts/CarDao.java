package com.etiya.recap.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.dtos.CarDetailDto;
import com.etiya.recap.entities.dtos.CarDetailWithCarImgDto;

@Repository
public interface CarDao extends JpaRepository<Car, Integer>{


	List<Car> getByBrand_brandId(int brandId);

	List<Car> getByColor_colorId(int colorId);

	List<Car> getByCity_cityId(int cityId);

	List<Car>getByCarIsAvailableIsTrue();

	List<Car>getByCarIsAvailableIsFalse();

	@Query("Select new com.etiya.recap.entities.dtos.CarDetailDto (c.id,b.brandName,cl.colorName,c.dailyPrice,c.kilometer,c.modelYear,city.cityName,c.findeksScore) "
			+ "From Car c Inner Join c.brand b Inner Join c.color cl Inner Join c.city city" )
	List<CarDetailDto> getCarWithDetails();

	@Query("Select new com.etiya.recap.entities.dtos.CarDetailWithCarImgDto (c.id,b.brandName,cl.colorName,c.dailyPrice,c.kilometer,ci.imagePath,city.cityName) "
			+ "From Car c Inner Join c.brand b Inner Join c.color cl Inner Join c.carImages ci Inner Join c.city city Where c.id=:id ")
	List<CarDetailWithCarImgDto> getCarWithCarImg(int id);

	@Query("Select c.findeksScore FROM Car c Where c.id=:carId")
	int getFindeksScoreByCarId(int carId);

	@Query("FROM Car c WHERE c.carIsAvailable=false AND c.id=:carId")
	boolean getCarReturnDateIsNull(int carId);
}


