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

import com.etiya.recap.business.abstracts.CorporateCustomerService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CorporateCustomerDto;
import com.etiya.recap.entities.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.etiya.recap.entities.requests.corporateCustomerRequests.DeleteCorporateCustomerRequest;
import com.etiya.recap.entities.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;

@RestController
@RequestMapping("/api/corporatecustomers")
public class CorporateCustomerController {

	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public CorporateCustomerController(CorporateCustomerService corporateCustomerService) {
		this.corporateCustomerService = corporateCustomerService;
	}
	
	@PostMapping("/addcorporatecustomer")
	public ResponseEntity<?> addCorporateCustomer(@Valid @RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		return ResponseEntity.ok(this.corporateCustomerService.add(createCorporateCustomerRequest));
	}

	@GetMapping("/getallcorporatecustomers")
	public DataResult<List<CorporateCustomerDto>> getAllCorporateCustomers() {
		return this.corporateCustomerService.getAll();
	}
	
	@GetMapping("/getcorporatecustomerbyid")
	public  DataResult<CorporateCustomerDto> getCorporateCustomerById(int id) {
		return this.corporateCustomerService.getById(id);
	}
	
	@DeleteMapping("/removecorporatecustomer")
	public Result removeCorporateCustomer(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
	}
	
	@PostMapping("/updatecorporatecustomer")
	public ResponseEntity<?> updateCorporateCustomer(@Valid @RequestBody UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		return ResponseEntity.ok(this.corporateCustomerService.update(updateCorporateCustomerRequest));
	}
	
	
}
