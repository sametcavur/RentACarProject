package com.etiya.recap.entities.dtos;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RentalDto {

	private int Id;

	private Date rentDate;

	private Date returnDate;
	
	private int rentalStartingCityId;

	private int returnCityId;
	
	private int kilometer;
	
	private String carBrandBrandName;
	
	private int carId;
	
	private int userId;
	
	private List<AdditionalServicesDto> additionalServices;
	
}
