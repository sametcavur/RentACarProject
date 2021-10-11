package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.IndividualCustomerDto;
import com.etiya.recap.entities.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiya.recap.entities.requests.individualCustomerRequests.DeleteIndividualCustomerRequest;
import com.etiya.recap.entities.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;

public interface IndividualCustomerService {
	
	DataResult<List<IndividualCustomerDto>> getAll();
	
	DataResult<IndividualCustomerDto> getById(int id);

	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
	
	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
	
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

}
