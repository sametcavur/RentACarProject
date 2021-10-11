package com.etiya.recap.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.City;

@Repository
public interface CityDao extends JpaRepository<City, Integer>{
	
	boolean existsCityBycityName(String cityName);

}
