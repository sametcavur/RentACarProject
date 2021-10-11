package com.etiya.recap.entities.dtos;

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
public class IndividualCustomerDto {
	private int customerId;

	private String firstName;

	private String lastName;

	private int identityNumber;
	
	private int userId;


}
