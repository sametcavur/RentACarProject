package com.etiya.recap.entities.requests.individualCustomerRequests;

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
public class CreateIndividualCustomerRequest {

	@NotNull
	private int userId;

	@NotBlank(message = "Boş olamaz")
	@NotNull
	private String firstName;

	@NotBlank(message = "Boş olamaz")
	@NotNull
	private String lastName;

	@NotNull
	private int identityNumber;

}
