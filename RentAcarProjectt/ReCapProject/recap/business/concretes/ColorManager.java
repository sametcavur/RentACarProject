package com.etiya.recap.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.ColorService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.ColorDao;
import com.etiya.recap.entities.concretes.Color;

@Service
public class ColorManager implements ColorService{

	private ColorDao colorDao;
	
	@Autowired
	public ColorManager(ColorDao colorDao) {
		this.colorDao = colorDao;
	}

	@Override
	public DataResult<List<Color>> getAll() {
		return new SuccessDataResult<List<Color>>(this.colorDao.findAll(), "Arabaların renkleri listelendi");
	}

	@Override
	public Result add(Color color) {
		this.colorDao.save(color);
		return new SuccessResult(true, "Renk başarıyla kaydedildi.");
	}

	@Override
	public DataResult<Color> getById(int id) {
		return new SuccessDataResult<Color>(this.colorDao.getById(id), "Araba listelendi");
	}

	@Override
	public Result delete(Color color) {
		this.colorDao.delete(color);
		return new SuccessResult(true, "Renk başarıyla silindi.");
		
	}

	@Override
	public Result update(Color color) {
		this.colorDao.save(color);
		return new SuccessResult(true, "Renk başarıyla güncellendi.");
		
	}

}
