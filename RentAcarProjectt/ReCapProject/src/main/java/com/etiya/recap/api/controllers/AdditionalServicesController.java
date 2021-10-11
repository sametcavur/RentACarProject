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

import com.etiya.recap.business.abstracts.AdditionalServicesService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.AdditionalServicesDto;
import com.etiya.recap.entities.requests.addionalServiceRequests.CreateAdditionalServicesRequest;
import com.etiya.recap.entities.requests.addionalServiceRequests.DeleteAdditionalServicesRequest;
import com.etiya.recap.entities.requests.addionalServiceRequests.UpdateAdditionalServicesRequest;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServicesController {
	
	private AdditionalServicesService additionalServicesService;

	@Autowired
	public AdditionalServicesController(AdditionalServicesService additionalServicesService) {
		super();
		this.additionalServicesService = additionalServicesService;
	}
	
	@PostMapping("/addadditionalservice")
	public ResponseEntity<?> addAdditionalService(@Valid @RequestBody CreateAdditionalServicesRequest createAdditionalServicesRequest){
		return ResponseEntity.ok(this.additionalServicesService.add(createAdditionalServicesRequest)) ;
	}
	
	@GetMapping("/getalladditionalservices")
	public DataResult<List<AdditionalServicesDto>> getAllAdditionalService(){
		return this.additionalServicesService.getAll();
	}
	
	@GetMapping("/getadditionalservicebyid")
	public DataResult<AdditionalServicesDto> getAdditionalServiceById(int id) {
		return this.additionalServicesService.getById(id);
	}
	
	@DeleteMapping("/removeadditionalservice")
	public Result removeAdditionalService(DeleteAdditionalServicesRequest deleteAdditionalServicesRequest) {
		return this.additionalServicesService.delete(deleteAdditionalServicesRequest);
	}
	
	@PostMapping("/updateadditionalservice")
	public ResponseEntity<?> updateAdditionalService(@Valid @RequestBody UpdateAdditionalServicesRequest updateAdditionalServicesRequest) {
		return ResponseEntity.ok(this.additionalServicesService.update(updateAdditionalServicesRequest));
	}

}
