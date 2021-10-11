package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.CityService;
import com.etiya.recap.business.constants.messages.CityMessages;
import com.etiya.recap.core.utilities.business.BusinessRules;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.ErrorResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.CityDao;
import com.etiya.recap.entities.concretes.City;
import com.etiya.recap.entities.dtos.CityDto;
import com.etiya.recap.entities.requests.cityRequests.CreateCityRequest;
import com.etiya.recap.entities.requests.cityRequests.DeleteCityRequest;
import com.etiya.recap.entities.requests.cityRequests.UpdateCityRequest;

@Service
public class CityManager implements CityService{

	private CityDao cityDao;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CityManager(CityDao cityDao,ModelMapper modelMapper) {
		this.cityDao = cityDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<CityDto>> getAll() {
		List<City> cities = this.cityDao.findAll();
		List<CityDto> cityDtos = cities.stream().map(city -> modelMapper.map(city, CityDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CityDto>>(cityDtos, CityMessages.GetAll);
	}

	@Override
	public DataResult<CityDto> getById(int id) {
		City city = this.cityDao.getById(id);
		CityDto cityDto = modelMapper.map(city, CityDto.class);
		return new SuccessDataResult<CityDto>(cityDto, CityMessages.GetById);
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) {
		var result = BusinessRules.run(checkCityNameDuplication(createCityRequest.getCityName()));

		if (result != null) {
			return result;
		}
		
		City city = new City();
		city.setCityName(createCityRequest.getCityName());
		this.cityDao.save(city);
		return new SuccessResult(true, CityMessages.Add); 
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {
		
		City city = new City();
		city.setCityId(deleteCityRequest.getId());
		this.cityDao.delete(city);
		return new SuccessResult(true,  CityMessages.Delete);
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		var result = BusinessRules.run(checkCityNameDuplication(updateCityRequest.getCityName()));

		if (result != null) {
			return result;
		}
		
		City city = this.cityDao.getById(updateCityRequest.getId());
		city.setCityName(updateCityRequest.getCityName());
		this.cityDao.save(city);
		return new SuccessResult(true,CityMessages.Update);
	}
	
	private Result checkCityNameDuplication(String cityName) {
		if (this.cityDao.existsCityBycityName(cityName)) {
			return new ErrorResult(CityMessages.ErrorCheckCityName);
		}

		return new SuccessResult();
	}

}
