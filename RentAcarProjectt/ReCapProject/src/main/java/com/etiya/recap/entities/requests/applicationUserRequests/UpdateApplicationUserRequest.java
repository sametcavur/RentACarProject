package com.etiya.recap.entities.requests.applicationUserRequests;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationUserRequest {
	
	@NotNull
	private int id;

	@NotBlank(message = "Boş olamaz")
	@NotNull
	@Email
	private String email;

	@NotBlank(message = "Boş olamaz")
	@NotNull
	@Size(min = 2, max = 30)
	private String password;

}
