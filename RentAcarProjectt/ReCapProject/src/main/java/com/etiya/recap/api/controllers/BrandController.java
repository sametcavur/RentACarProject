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

import com.etiya.recap.business.abstracts.BrandService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.BrandDto;
import com.etiya.recap.entities.requests.brandRequests.CreateBrandRequest;
import com.etiya.recap.entities.requests.brandRequests.DeleteBrandRequest;
import com.etiya.recap.entities.requests.brandRequests.UpdateBrandRequest;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
	
	private BrandService brandService;
	
	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	@PostMapping("/addbrand")
	public ResponseEntity<?> addBrand(@Valid @RequestBody CreateBrandRequest createBrandRequest){
		return ResponseEntity.ok(this.brandService.add(createBrandRequest)) ;
	}
	
	@GetMapping("/getallbrands")
	public DataResult<List<BrandDto>> getAllBrands(){
		return this.brandService.getAll();
	}
	
	@GetMapping("/getbrandbyid")
	public DataResult<BrandDto> getBrandById(int id) {
		return this.brandService.getById(id);
	}
	
	@DeleteMapping("/removebrand")
	public Result removeBrand(DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
	}
	
	@PostMapping("/updatebrand")
	public ResponseEntity<?> updateBrand(@Valid @RequestBody UpdateBrandRequest updateBrandRequest) {
		return ResponseEntity.ok(this.brandService.update(updateBrandRequest)) ;
	}
}
