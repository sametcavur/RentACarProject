package com.etiya.recap.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarDetailDto {
	
	private int id;
	
	private String brandName;
	
	private String colorName;
	
	private double dailyPrice;

}
