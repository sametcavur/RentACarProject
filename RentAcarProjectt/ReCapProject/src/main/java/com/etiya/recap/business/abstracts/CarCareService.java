package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CarCareDto;
import com.etiya.recap.entities.requests.carCareRequests.CreateCarCareRequest;
import com.etiya.recap.entities.requests.carCareRequests.DeleteCarCareRequest;
import com.etiya.recap.entities.requests.carCareRequests.UpdateCarCareRequest;

public interface CarCareService {
	
	DataResult<List<CarCareDto>> getAll();

	Result sendCarToCare(CreateCarCareRequest createCarCareRequest);
	
	DataResult<CarCareDto> getById(int id);
	
	Result delete(DeleteCarCareRequest deleteCarCareRequest);
	
	Result update(UpdateCarCareRequest updateCarCareRequest);

}
