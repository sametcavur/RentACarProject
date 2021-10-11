package com.etiya.recap.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.CarDamageInformation;

@Repository
public interface CarDamageInformationDao extends JpaRepository<CarDamageInformation, Integer>{
	
	List<CarDamageInformation> getByCar_id(int carId);

}
