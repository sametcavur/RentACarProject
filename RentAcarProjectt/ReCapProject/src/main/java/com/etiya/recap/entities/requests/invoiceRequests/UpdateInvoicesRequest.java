package com.etiya.recap.entities.requests.invoiceRequests;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoicesRequest {
	
	private int id;
	
	private Date creationDate;

	private int rentalId;

	private long totalRentDateCount;
	
	private double rentPrice;

}
