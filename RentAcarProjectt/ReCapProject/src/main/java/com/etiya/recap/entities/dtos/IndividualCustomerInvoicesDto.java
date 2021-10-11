package com.etiya.recap.entities.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndividualCustomerInvoicesDto {

	private int invoiceNumber;

	private String firstName;
	
	private String lastName;
	
	private int identityNumber;

	private Date creationDate;

	private double rentDateCount;

	private double rentPrice;
}
