package com.etiya.recap.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.recap.business.abstracts.IndividualCustomerService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.IndividualCustomerDto;
import com.etiya.recap.entities.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiya.recap.entities.requests.individualCustomerRequests.DeleteIndividualCustomerRequest;
import com.etiya.recap.entities.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomerController {

	private IndividualCustomerService individualCustomerService;
	
	@Autowired
	public IndividualCustomerController(IndividualCustomerService individualCustomerService) {
		this.individualCustomerService = individualCustomerService;
	}
	
	@PostMapping("/addindividualcustomer")
	public ResponseEntity<?> addIndividualCustomer(@Valid @RequestBody CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		return ResponseEntity.ok(this.individualCustomerService.add(createIndividualCustomerRequest));
	}

	@GetMapping("/getallindividualcustomers")
	public DataResult<List<IndividualCustomerDto>> getAllIndividualCustomers() {
		return this.individualCustomerService.getAll();
	}
	
	@GetMapping("/getindividualcustomerbyid")
	public  DataResult<IndividualCustomerDto> getIndividualCustomerById(int id) {
		return this.individualCustomerService.getById(id);
	}
	
	@DeleteMapping("/removeindividualcustomer")
	public Result removeIndividualCustomer(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}
	
	@PostMapping("/updateindividualcustomer")
	public ResponseEntity<?> updateIndividualCustomer(@Valid @RequestBody UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return ResponseEntity.ok(this.individualCustomerService.update(updateIndividualCustomerRequest));
	}
	
	
}
