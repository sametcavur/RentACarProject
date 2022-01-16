package com.etiya.recap.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.recap.business.abstracts.CustomerService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.concretes.Customer;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private CustomerService customerService;

	
	@Autowired
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	
	@PostMapping("/addcustomer")
	public Result addCustomer(Customer customer) {
		return this.customerService.add(customer);
	}

	@GetMapping("/getallcustomers")
	public DataResult<List<Customer>> getAllCustomers() {
		return this.customerService.getAll();
	}
	
	@GetMapping("/getcustomerbyid")
	public  DataResult<Customer> getCustomerById(int id) {
		return this.customerService.getById(id);
	}
	
	
	@DeleteMapping("/removecustomer")
	public Result removeCar(Customer customer) {
		return this.customerService.delete(customer);
	}
	
	@PostMapping("/updatecustomer")
	public Result updateCar(Customer customer) {
		return this.customerService.update(customer);
	}
	
	
	

}
