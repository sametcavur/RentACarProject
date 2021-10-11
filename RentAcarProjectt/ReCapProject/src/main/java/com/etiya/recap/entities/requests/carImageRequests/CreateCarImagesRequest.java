package com.etiya.recap.entities.requests.carImageRequests;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarImagesRequest {

	private int carId;
	private MultipartFile file;

}
