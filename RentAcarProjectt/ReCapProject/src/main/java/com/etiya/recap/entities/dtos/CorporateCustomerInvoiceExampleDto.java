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
public class CorporateCustomerInvoiceExampleDto {
	
	private int invoiceNumber;
	
	private String companyName;
	
	private int taxNumber;
	
	private Date creationDate;
	
	private double rentDateCount;
	
	private double rentPrice;
	

}
