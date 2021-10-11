package com.etiya.recap.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.CustomerService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.CustomerDao;
import com.etiya.recap.entities.concretes.Customer;
@Service
public class CustomerManager implements CustomerService {
	
	private CustomerDao customerDao;
	
    @Autowired
	public CustomerManager(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	@Override
	public DataResult<List<Customer>> getAll() {
		return new SuccessDataResult<List<Customer>>(this.customerDao.findAll(), "Müşteriler başarıyla listelendi");
	}

	@Override
	public Result add(Customer customer) {
		this.customerDao.save(customer);
		return new SuccessResult(true,"Müşteri eklendi");
	}

	@Override
	public DataResult<Customer> getById(int id) {
		
		return new SuccessDataResult<Customer>(this.customerDao.getById(id), "Müşteriler başarıyla listelendi");
		
	}

	@Override
	public Result delete(Customer customer) {
		this.customerDao.delete(customer);
		return new SuccessResult(true,"Müşteri silindi.");
	}

	@Override
	public Result update(Customer customer) {
		this.customerDao.save(customer);
		return new SuccessResult(true,"Müşteri güncellendi.");
	}

}
