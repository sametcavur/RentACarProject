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

import com.etiya.recap.business.abstracts.CarCareService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CarCareDto;
import com.etiya.recap.entities.requests.carCareRequests.CreateCarCareRequest;
import com.etiya.recap.entities.requests.carCareRequests.DeleteCarCareRequest;
import com.etiya.recap.entities.requests.carCareRequests.UpdateCarCareRequest;

@RestController
@RequestMapping("/api/carcares")
public class CarCareController {
	
	private CarCareService carCareService;
	
	@Autowired
	public CarCareController(CarCareService carCareService) {
		this.carCareService = carCareService;
	}
	

	@GetMapping("/getallcarsincare")
	public DataResult<List<CarCareDto>> getAllCarsInCare(){
		return this.carCareService.getAll();
	}

	@PostMapping("/sendcartocare")
	public ResponseEntity<?> sendCarToCare(@Valid @RequestBody CreateCarCareRequest createCarCareRequest){
		return ResponseEntity.ok(this.carCareService.sendCarToCare(createCarCareRequest)) ;
	}
	
	@GetMapping("/getcarincareinfobyid")
	public DataResult<CarCareDto> getCarInCareInfoById(int id) {
		return this.carCareService.getById(id);
	}
	
	@DeleteMapping("/removecarincare")
	public Result removeCarInCare(DeleteCarCareRequest deleteCarCareRequest) {
		return this.carCareService.delete(deleteCarCareRequest);
	}
	
	@PostMapping("/updatecarincare")
	public ResponseEntity<?> updateCarInCare(@Valid @RequestBody UpdateCarCareRequest updateCarCareRequest) {
		return ResponseEntity.ok(this.carCareService.update(updateCarCareRequest)) ;
	}
}
