package com.etiya.recap.business.abstracts;

import java.io.IOException;
import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CarImageDto;
import com.etiya.recap.entities.requests.carImageRequests.CreateCarImagesRequest;
import com.etiya.recap.entities.requests.carImageRequests.DeleteCarImagesRequest;

public interface CarImagesService {
	
	DataResult<List<CarImageDto>> getAll();

	Result add(CreateCarImagesRequest createCarImagesRequest) throws IOException;
	
	DataResult <List<CarImageDto>> getByCarId(int carId);
	
	Result delete(DeleteCarImagesRequest deleteCarImagesRequest);
	
	
	
}
