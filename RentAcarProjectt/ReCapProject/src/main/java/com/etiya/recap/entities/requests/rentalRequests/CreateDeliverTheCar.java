package com.etiya.recap.entities.requests.rentalRequests;

import java.util.Date;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeliverTheCar {
	
	@NotNull
	private int rentalId;
	
	
	private Date returnDate;

}
