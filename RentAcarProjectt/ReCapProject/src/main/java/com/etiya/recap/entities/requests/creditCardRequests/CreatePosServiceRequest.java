package com.etiya.recap.entities.requests.creditCardRequests;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePosServiceRequest {
	
	private String nameOnTheCard;

	private String cardNumber;

	private Date expirationDate;

	private String cvc;
	
	private double feePayable;

}
