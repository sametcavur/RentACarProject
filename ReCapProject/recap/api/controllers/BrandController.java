package com.etiya.recap.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.recap.business.abstracts.BrandService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.concretes.Brand;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
	
	private BrandService brandService;
	
	
	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}


	@PostMapping("/addbrand")
	public Result addBrand(Brand brand){
		return this.brandService.add(brand);
	}
	
	
	@GetMapping("/getallbrands")
	public DataResult<List<Brand>> getAllBrands(){
		return this.brandService.getAll();
	}
	
	@GetMapping("/getbrandbyid")
	public DataResult<Brand> getColorById(int id) {
		return this.brandService.getById(id);
	}
	
	@DeleteMapping("/removebrand")
	public Result removeBrand(Brand brand) {
		return this.brandService.delete(brand);
	}
	
	@PostMapping("/updatebrand")
	public Result updateBrand(Brand brand) {
		return this.brandService.update(brand);
	}

}
