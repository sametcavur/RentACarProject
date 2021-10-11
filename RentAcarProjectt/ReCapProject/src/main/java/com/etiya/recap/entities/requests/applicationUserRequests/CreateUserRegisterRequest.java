package com.etiya.recap.entities.requests.applicationUserRequests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUserRegisterRequest {

	@NotBlank(message = "Boş olamaz")
	@NotNull
	@Email
	private String email;
	

	@NotBlank(message = "Boş olamaz")
	@NotNull
	@Size(min = 2 , max = 30)
	private String password;

}
