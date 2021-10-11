package com.etiya.recap.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.CarImages;
@Repository
public interface CarImagesDao extends JpaRepository<CarImages, Integer> {
	
	int countByCar_id(int carId);
	
	List<CarImages> getByCar_id(int carId);
	
	boolean existsByCar_id(int carId);
	
	
}
