package com.etiya.recap.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.recap.entities.concretes.AdditionalServices;

public interface AdditionalServicesDao extends JpaRepository<AdditionalServices, Integer> {
	
	boolean existsAdditionalServicesByadditionalServiceName(String additionalServiceName);


}
