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

import com.etiya.recap.business.abstracts.CarService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CarDetailDto;
import com.etiya.recap.entities.requests.carRequests.CreateCarRequest;
import com.etiya.recap.entities.requests.carRequests.DeleteCarRequest;
import com.etiya.recap.entities.requests.carRequests.UpdateCarAvailableRequest;
import com.etiya.recap.entities.requests.carRequests.UpdateCarRequest;


@RestController
@RequestMapping("/api/cars")
public class CarController {
	
	private CarService carService;

	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping("/addcar")
	public ResponseEntity<?> addCar(@Valid @RequestBody CreateCarRequest createCarRequest) {
		return ResponseEntity.ok(this.carService.add(createCarRequest));
	}
	
	@GetMapping("/getallcars")
	public DataResult<List<CarDetailDto>> getAllCars() {
		return this.carService.getAll();
	}
	
	@GetMapping("/getallcarsincare")
	public DataResult<List<CarDetailDto>> getAllCarsInCare(){
		return this.carService.getAllCarsInCare();
	}
	
	@GetMapping("/getallcarsarenotincare")
	public DataResult<List<CarDetailDto>> getAllCarsAreNotInCare(){
		return this.carService.getAllCarsNotInCare();
	}
	
	@GetMapping("/getcarsbycityıd")
	public DataResult<List<CarDetailDto>> getCarsByCityId(int cityId) {
		return this.carService.getCarsByCityId(cityId);
	}
	
	@GetMapping("/getcarbycarid")
	public  DataResult<CarDetailDto> getCarByCarId(int id) {
		return this.carService.getById(id);
	}
	
	@DeleteMapping("/removecar")
	public Result removeCar(DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}
	
	@PostMapping("/updatecar")
	public ResponseEntity<?> updateCar(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
		return ResponseEntity.ok(this.carService.update(updateCarRequest));
	}
	
	@PostMapping("/updatecarisavailable")
	public ResponseEntity<?> updateCarIsAvailable(@Valid @RequestBody UpdateCarAvailableRequest updateCarAvailableRequest) {
		return ResponseEntity.ok(this.carService.updateCarAvailable(updateCarAvailableRequest));
	}

	@GetMapping("/getcarbybrandid")
	public  DataResult<List<CarDetailDto>> getCarByBrandId(int brandId) {
		return this.carService.getCarByBrandId(brandId);
	}
	
	@GetMapping("/getcarbycolorid")
	public  DataResult<List<CarDetailDto>> getCarByColorId(int colorId) {
		return this.carService.getCarByColorId(colorId);
	}
	
}
