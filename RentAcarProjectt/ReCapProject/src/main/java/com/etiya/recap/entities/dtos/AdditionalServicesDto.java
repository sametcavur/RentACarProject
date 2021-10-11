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
public class AdditionalServicesDto {
	
	private int id;
	
	private String additionalServiceName;
	
	private double additionalServicePrice;
	

}
