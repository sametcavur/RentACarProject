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
public class CarDamageInformationDto {
	
	private int id;
	
	private String description;
	
	private double damagePrice;
	
	private int carId;

}
