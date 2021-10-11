package com.etiya.recap.entities.requests.rentalRequests;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.etiya.recap.entities.dtos.CreditCardDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

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
	
	private CreditCardDto creditCardDto;
	
	private boolean saveCreditCard;
	
	private List<Integer> additionalServicesId;
	
	
	
}
