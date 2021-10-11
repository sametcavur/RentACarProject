package com.etiya.recap.entities.requests.corporateCustomerRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
	
	@NotNull
	private int userId;
	
	@NotBlank(message = "Boş olamaz")
	@NotNull
	private String companyName;
	
	@NotNull
	private int taxNumber;

}
