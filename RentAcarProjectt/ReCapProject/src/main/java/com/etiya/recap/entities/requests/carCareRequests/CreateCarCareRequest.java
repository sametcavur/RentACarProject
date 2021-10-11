package com.etiya.recap.entities.requests.carCareRequests;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarCareRequest {
	
	@NotNull
	private Date carCareStartDate;
	
	@NotNull
	private Date carCareFinishDate;
	
	@NotNull
	private int carId;
	

}
