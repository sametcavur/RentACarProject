package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CarDetailDto;
import com.etiya.recap.entities.dtos.CarDetailWithCarImgDto;
import com.etiya.recap.entities.requests.carRequests.CreateCarRequest;
import com.etiya.recap.entities.requests.carRequests.DeleteCarRequest;
import com.etiya.recap.entities.requests.carRequests.UpdateCarAvailableRequest;
import com.etiya.recap.entities.requests.carRequests.UpdateCarRequest;

public interface CarService {

	DataResult<List<CarDetailDto>> getAll();

	DataResult<List<CarDetailDto>> getAllCarsInCare();

	DataResult<List<CarDetailDto>> getAllCarsNotInCare();

	DataResult<List<CarDetailDto>> getCarsByCityId(int city);

	DataResult<CarDetailDto> getById(int id);

	DataResult<List<CarDetailDto>> getCarByColorId(int colorId);

	DataResult<List<CarDetailDto>> getCarByBrandId(int brandId);

	Result add(CreateCarRequest createCarRequest);

	Result delete(DeleteCarRequest deleteCarRequest);

	Result update(UpdateCarRequest updateCarRequest);

	Result updateCarAvailable(UpdateCarAvailableRequest updateCarAvailableRequest);
	
	
	

	DataResult<List<CarDetailWithCarImgDto>> getCarWithCarImgByCarId(int id);


}
