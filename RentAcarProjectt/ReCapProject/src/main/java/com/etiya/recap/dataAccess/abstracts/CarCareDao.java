package com.etiya.recap.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.CarCare;

@Repository
public interface CarCareDao extends JpaRepository<CarCare, Integer> {
	
	List<CarCare> getCarCareByCar_id(int carId);
	
	
}
