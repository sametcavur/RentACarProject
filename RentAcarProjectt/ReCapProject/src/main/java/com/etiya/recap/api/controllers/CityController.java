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

import com.etiya.recap.business.abstracts.CityService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CityDto;
import com.etiya.recap.entities.requests.cityRequests.CreateCityRequest;
import com.etiya.recap.entities.requests.cityRequests.DeleteCityRequest;
import com.etiya.recap.entities.requests.cityRequests.UpdateCityRequest;

@RestController
@RequestMapping("/api/cities")
public class CityController {
	
	private CityService cityService;

	@Autowired
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@PostMapping("/addcity")
	public ResponseEntity<?> addCity(@Valid @RequestBody CreateCityRequest createCityRequest) {
		return ResponseEntity.ok(this.cityService.add(createCityRequest));
	}

	@GetMapping("/getallcities")
	public DataResult<List<CityDto>> getAllCities() {
		return this.cityService.getAll();
	}
	
	@GetMapping("/getcitybyid")
	public DataResult<CityDto> getCityById(int id) {
		return this.cityService.getById(id);
	}
	
	@DeleteMapping("/removecity")
	public Result removeCity(DeleteCityRequest deleteCityRequest) {
		return this.cityService.delete(deleteCityRequest);
	}
	
	@PostMapping("/updatecity")
	public ResponseEntity<?> updateCity(@Valid @RequestBody  UpdateCityRequest updateCityRequest) {
		return ResponseEntity.ok(this.cityService.update(updateCityRequest));
	}
}
