package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.AdditionalServicesDto;
import com.etiya.recap.entities.requests.addionalServiceRequests.CreateAdditionalServicesRequest;
import com.etiya.recap.entities.requests.addionalServiceRequests.DeleteAdditionalServicesRequest;
import com.etiya.recap.entities.requests.addionalServiceRequests.UpdateAdditionalServicesRequest;

public interface AdditionalServicesService {
	
	DataResult<List<AdditionalServicesDto>> getAll();

	Result add(CreateAdditionalServicesRequest createAdditionalServicesRequest);
	
	DataResult<AdditionalServicesDto> getById(int id);
	
	Result delete(DeleteAdditionalServicesRequest deleteAdditionalServicesRequest);
	
	Result update(UpdateAdditionalServicesRequest updateAdditionalServicesRequest);
	
	
}


