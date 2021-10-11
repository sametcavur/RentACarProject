package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CorporateCustomerDto;
import com.etiya.recap.entities.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.etiya.recap.entities.requests.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.etiya.recap.entities.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;

public interface CorporateCustomerService {
	
	DataResult<List<CorporateCustomerDto>> getAll();
	
	DataResult<CorporateCustomerDto> getById(int id);

	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
	
	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
	
	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

}
