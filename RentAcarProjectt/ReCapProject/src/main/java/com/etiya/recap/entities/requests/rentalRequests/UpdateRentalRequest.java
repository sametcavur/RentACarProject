package com.etiya.recap.entities.requests.rentalRequests;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	
	@NotNull
	private int id;
	
	@NotNull(message = "Boş geçilemez")
	private Date rentDate;
	
	private Date returnDate;
	
	@NotNull
	private int returnCityId;
	
	@NotNull
	private int kilometer;

	@NotNull
	private int carId;

	@NotNull
	private int userId;
	
	
	private List<Integer> additionalServicesId;

}
