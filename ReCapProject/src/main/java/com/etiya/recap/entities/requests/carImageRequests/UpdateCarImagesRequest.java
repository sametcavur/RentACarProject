package com.etiya.recap.entities.requests.carImageRequests;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarImagesRequest {

	@NotNull
	private int id;

	private int carId;

	private MultipartFile file;

}
