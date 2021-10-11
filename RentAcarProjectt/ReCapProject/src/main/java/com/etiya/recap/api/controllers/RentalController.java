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

import com.etiya.recap.business.abstracts.RentalService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.RentalDto;
import com.etiya.recap.entities.requests.rentalRequests.CreateDeliverTheCar;
import com.etiya.recap.entities.requests.rentalRequests.CreateRentalRequest;
import com.etiya.recap.entities.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.recap.entities.requests.rentalRequests.UpdateRentalRequest;


@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	
	private RentalService rentalService;
	
	@Autowired
	public RentalController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	
	@PostMapping("/renttocorporatecustomer")
	public ResponseEntity<?> rentToCorporateCustomer(@Valid  @RequestBody CreateRentalRequest createRentalRequest) {
		return ResponseEntity.ok(this.rentalService.rentCorporateCustomer(createRentalRequest)) ;
	}
	
	@PostMapping("/renttoindividualcustomer")
	public ResponseEntity<?> rentToIndividualCustomer(@Valid  @RequestBody CreateRentalRequest createRentalRequest) {
		return ResponseEntity.ok(this.rentalService.rentIndividualCustomer(createRentalRequest)) ;
	}
	
	@GetMapping("/getallrentals")
	public DataResult<List<RentalDto>> getAllRentals() {
		return this.rentalService.getAll();
	}
	
	@GetMapping("/getrentalbyid")
	public  DataResult<RentalDto> getRentalById(int id) {
		
		return this.rentalService.getById(id);
	}
	
	@DeleteMapping("/removerental")
	public Result removeRental( DeleteRentalRequest deleteRentalRequest) {
		return this.rentalService.delete(deleteRentalRequest);
	}
	
	@PostMapping("/updatecorporaterental")
	public ResponseEntity<?> updateCorporateRental(@Valid @RequestBody  UpdateRentalRequest updateRentalRequest) {
		return ResponseEntity.ok(this.rentalService.updateCorporateCustomerRent(updateRentalRequest));
	}
	
	@PostMapping("/updateindividualrental")
	public ResponseEntity<?> updateIndividualRental(@Valid @RequestBody  UpdateRentalRequest updateRentalRequest) {
		return ResponseEntity.ok(this.rentalService.updateIndividualCustomerRent(updateRentalRequest));
	}
	
	@PostMapping("/delivercorporatecustomercar")
	public Result deliverCorporateCustomerCar(@RequestBody   CreateDeliverTheCar createDeliverTheCar) {
		return this.rentalService.deliverCorporateCustomerCar(createDeliverTheCar);
	}
	
	@PostMapping("/deliverindividualcustomercar")
	public	Result deliverIndividualCustomerCar(@RequestBody CreateDeliverTheCar createDeliverTheCar) {
		return this.rentalService.deliverIndividualCustomerCar(createDeliverTheCar);
	}
	
	
}
