package com.etiya.recap.entities.requests.carRequests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@JsonIgnore
	private int id;

	@NotNull
	@Min(1900)
	private int modelYear;

	@NotNull
	@Min(0)
	private double dailyPrice;

	@NotNull
	@Min(0)
	@Max(1900)
	private int findeksScore;
	
	@NotNull
	private int kilometer;
	
	@NotBlank
	@NotNull
	private String description;

	@NotNull
	private int cityId;
	
	@NotNull
	private int brandId;
	
	@NotNull
	private int colorId;

}
