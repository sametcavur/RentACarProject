package com.etiya.recap.entities.requests.carDamageInfoRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageInfoRequest {
	
	@NotNull
	@NotBlank
	private String description;
	
	@NotNull
	private double damagePrice;
	
	@NotNull
	private int carId;

}
