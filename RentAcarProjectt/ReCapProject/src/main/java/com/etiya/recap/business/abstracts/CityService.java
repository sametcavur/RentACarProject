package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CityDto;
import com.etiya.recap.entities.requests.cityRequests.CreateCityRequest;
import com.etiya.recap.entities.requests.cityRequests.DeleteCityRequest;
import com.etiya.recap.entities.requests.cityRequests.UpdateCityRequest;

public interface CityService {
	
	DataResult<List<CityDto>> getAll();
	
	DataResult<CityDto> getById(int id);
	
	Result add(CreateCityRequest createCityRequest);
	
	Result delete(DeleteCityRequest deleteCityRequest);
	
	Result update(UpdateCityRequest updateCityRequest);
	

}
