package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.BrandService;
import com.etiya.recap.business.constants.messages.BrandMessages;
import com.etiya.recap.core.utilities.business.BusinessRules;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.ErrorResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.BrandDao;
import com.etiya.recap.entities.concretes.Brand;
import com.etiya.recap.entities.dtos.BrandDto;
import com.etiya.recap.entities.requests.brandRequests.CreateBrandRequest;
import com.etiya.recap.entities.requests.brandRequests.DeleteBrandRequest;
import com.etiya.recap.entities.requests.brandRequests.UpdateBrandRequest;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private  ModelMapper modelMapper;

	@Autowired
	public BrandManager(BrandDao brandDao,ModelMapper modelMapper) {
		this.brandDao = brandDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<BrandDto>> getAll() {
		List<Brand> brands = this.brandDao.findAll();
		List<BrandDto> brandsDtos = brands
				.stream()
				.map(brand -> modelMapper.map(brand, BrandDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<>(brandsDtos, BrandMessages.GetAll);
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {

		Brand brand = new Brand();
		brand.setBrandName(createBrandRequest.getBrandName());

		var result = BusinessRules.run(checkBrandNameDuplication(createBrandRequest.getBrandName()));

		if (result != null) {
			return result;
		}

		this.brandDao.save(brand);
		return new SuccessResult(true, BrandMessages.Add);
	}

	@Override
	public DataResult<BrandDto> getById(int id) {
		Brand brands = this.brandDao.getById(id);
		BrandDto brandDto = modelMapper.map(brands, BrandDto.class);
		return new SuccessDataResult<>(brandDto, BrandMessages.GetById);
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {

		Brand brand = new Brand();
		brand.setBrandId(deleteBrandRequest.getId());

		this.brandDao.delete(brand);
		return new SuccessResult(true, BrandMessages.Delete);
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {

		var result = BusinessRules.run(checkBrandNameDuplication(updateBrandRequest.getBrandName()));

		if (result != null) {
			return result;
		}

		Brand brand = this.brandDao.getById(updateBrandRequest.getId());
		brand.setBrandId(updateBrandRequest.getId());
		brand.setBrandName(updateBrandRequest.getBrandName());

		this.brandDao.save(brand);
		return new SuccessResult(true, BrandMessages.Update);

	}

	private Result checkBrandNameDuplication(String brandName) {
		if (this.brandDao.existsBrandBybrandName(brandName)) {
			return new ErrorResult(BrandMessages.ErrorCheckBrandName);
		}

		return new SuccessResult();
	}
}
