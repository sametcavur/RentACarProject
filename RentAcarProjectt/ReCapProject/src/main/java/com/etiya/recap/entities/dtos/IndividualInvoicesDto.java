package com.etiya.recap.entities.dtos;

import java.util.Date;

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
public class IndividualInvoicesDto {

	private int id;

	private int invoiceNumber;

	private Date creationDate;

	private long rentDateCount;

	private double rentPrice;
	
	private int rentalId;
	
	private int customerId;


}
