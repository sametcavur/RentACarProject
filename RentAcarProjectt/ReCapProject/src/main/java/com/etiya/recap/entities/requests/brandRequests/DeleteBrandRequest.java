package com.etiya.recap.entities.requests.brandRequests;

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
public class DeleteBrandRequest {
	
	@NotBlank(message = "Boş olamaz")
	@NotNull
	private int id;

	

}
