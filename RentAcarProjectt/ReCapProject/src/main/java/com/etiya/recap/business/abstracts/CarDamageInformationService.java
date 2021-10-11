package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CarDamageInformationDto;
import com.etiya.recap.entities.requests.carDamageInfoRequests.CreateCarDamageInfoRequest;
import com.etiya.recap.entities.requests.carDamageInfoRequests.DeleteCarDamageInfoRequest;
import com.etiya.recap.entities.requests.carDamageInfoRequests.UpdateCarDamageInformationRequest;


public interface CarDamageInformationService {
	
	DataResult<List<CarDamageInformationDto>> getAll();
	    
	DataResult<CarDamageInformationDto> getById(int id);
	
	DataResult<List<CarDamageInformationDto>> getCarDamageInfoByCarId(int carId);
		
	Result add(CreateCarDamageInfoRequest createCarDamageInfoRequest);
		
	Result delete(DeleteCarDamageInfoRequest deleteCarDamageInfoRequest);
		
	Result update(UpdateCarDamageInformationRequest updateCarDamageInformationRequest);

}
