package com.etiya.recap.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.dtos.CarDetailDto;


@Repository
public interface CarDao extends JpaRepository<Car, Integer>{

	@Query("Select new com.etiya.recap.entities.dtos.CarDetailDto (c.id,b.brandName,cl.colorName,c.dailyPrice) From Car c Inner Join c.brand b Inner Join c.color cl")
			List<CarDetailDto> getCarWithDetails();
}


