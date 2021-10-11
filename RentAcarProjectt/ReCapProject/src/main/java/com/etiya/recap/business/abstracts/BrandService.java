package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.BrandDto;
import com.etiya.recap.entities.requests.brandRequests.CreateBrandRequest;
import com.etiya.recap.entities.requests.brandRequests.DeleteBrandRequest;
import com.etiya.recap.entities.requests.brandRequests.UpdateBrandRequest;

public interface BrandService {

	DataResult<List<BrandDto>> getAll();

	Result add(CreateBrandRequest createBrandRequest);
	
	DataResult<BrandDto> getById(int id);
	
	Result delete(DeleteBrandRequest deleteBrandRequest);
	
	Result update(UpdateBrandRequest updateBrandRequest);
}
