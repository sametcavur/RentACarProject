package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.concretes.Customer;

public interface CustomerService {
	
	DataResult<List<Customer>> getAll();

	Result add(Customer customer);
	
	DataResult<Customer> getById(int id);
	
	Result delete(Customer customer);
	
	Result update(Customer customer);

}
