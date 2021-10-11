package com.etiya.recap.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.Rental;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer> {
	
	//IsEmpty-IsNull-IsTrue
	Rental getByCar_idAndReturnDateIsNull(int carId);
	
	

}
