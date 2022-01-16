package com.etiya.recap.api.controllers;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.etiya.recap.business.concretes.BrandManager;
import com.etiya.recap.dataAccess.abstracts.BrandDao;
import com.etiya.recap.entities.concretes.Brand;
import com.etiya.recap.entities.concretes.Car;

@ExtendWith(MockitoExtension.class)
class BrandControllerTest {
	
	@Mock
	private BrandManager brandManager;
	
	@InjectMocks
	private BrandDao brandDao;
	
	private Brand brandTest;
	

	@BeforeEach
	void setUp(TestInfo testInfo) {
	    if(testInfo.getTags().contains("Assert Throw Denemesi")){
	    	List<Car> cars = new ArrayList<Car>();
	    	this.brandTest = new Brand(1, "pembe", cars);
	    }else{
	    	this.brandTest = null;
	    }
	}


	@Test
	@Tag("Assert Equals Denemesi")
	void shouldReturnAllBrandsInDB() {
		List<Brand> brands = new ArrayList<Brand>();
		List<Brand> brandDtos = brandDao.findAll();
		System.out.println("11");
		assertEquals(brands, brandDtos);
	}
	
	@Test
	@Tag("Assert Samet Denemesi")
	void shouldReturnOnlyOneBrandFromDB() {
		Brand brandDtos =mock(Brand.class);
		Brand brand = brandDao.getById(1);
		brandDtos = brand;
		assertSame(brand,brandDtos);
	}
	
	
}
