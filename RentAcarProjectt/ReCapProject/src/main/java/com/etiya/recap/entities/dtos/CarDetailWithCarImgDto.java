package com.etiya.recap.entities.dtos;

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
public class CarDetailWithCarImgDto {

	private int id;

	private String brandName;

	private String colorName;
	
	private double dailyPrice;
	
	private int kilometer;
	
	private String cityName;

	private String carImages;
	

}
