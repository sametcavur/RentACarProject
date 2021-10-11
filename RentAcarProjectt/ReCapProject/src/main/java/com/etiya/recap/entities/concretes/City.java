package com.etiya.recap.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "city")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "cars","StartingCityRentals","returnCityRentals"})
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private int cityId;

	@Column(name = "cityName")
	private String cityName;

	@OneToMany(mappedBy = "city")
	private List<Car> cars;

	@OneToMany(mappedBy = "rentalStartingCity")
	private List<Rental> StartingCityRentals;

	@OneToMany(mappedBy = "returnCity")
	private List<Rental> returnCityRentals;
	
	
	

}