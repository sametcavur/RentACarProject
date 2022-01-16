package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.concretes.Brand;

public interface BrandService {

	DataResult<List<Brand>> getAll();

	Result add(Brand brand);
	
	DataResult<Brand> getById(int id);
	
	Result delete(Brand brand);
	
	Result update(Brand brand);
}
