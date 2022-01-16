package com.etiya.recap.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.BrandService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.BrandDao;
import com.etiya.recap.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService{

	
	private BrandDao brandDao;
	
	@Autowired
	public BrandManager(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	@Override
	public DataResult<List<Brand>> getAll() {
		return new SuccessDataResult<List<Brand>>(this.brandDao.findAll(),"Markalar başarıyla listelendi") ;
	}

	@Override
	public Result add(Brand brand) {
		this.brandDao.save(brand);
		return new SuccessResult(true,"Marka başarıyla eklendi");
	}

	@Override
	public DataResult<Brand> getById(int id) {
		return new SuccessDataResult<Brand>(this.brandDao.getById(id),"Marka başarıyla listelendi") ;
	}

	@Override
	public Result delete(Brand brand) {
		this.brandDao.delete(brand);
		return new SuccessResult(true,"Marka başarıyla silindi");
	}

	@Override
	public Result update(Brand brand) {
		this.brandDao.save(brand);
		return new SuccessResult(true,"Marka başarıyla güncellendi");
		
	}

}
