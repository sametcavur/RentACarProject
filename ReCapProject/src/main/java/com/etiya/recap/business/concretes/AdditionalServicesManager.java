package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.AdditionalServicesService;
import com.etiya.recap.business.constants.messages.AdditionalServiceMessages;
import com.etiya.recap.core.utilities.business.BusinessRules;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.ErrorResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.AdditionalServicesDao;
import com.etiya.recap.entities.concretes.AdditionalServices;
import com.etiya.recap.entities.dtos.AdditionalServicesDto;
import com.etiya.recap.entities.requests.addionalServiceRequests.CreateAdditionalServicesRequest;
import com.etiya.recap.entities.requests.addionalServiceRequests.DeleteAdditionalServicesRequest;
import com.etiya.recap.entities.requests.addionalServiceRequests.UpdateAdditionalServicesRequest;

@Service
public class AdditionalServicesManager implements AdditionalServicesService {

	private AdditionalServicesDao additionalServicesDao;
	private ModelMapper modelMapper;

	@Autowired
	public AdditionalServicesManager(AdditionalServicesDao additionalServicesDao,ModelMapper modelMapper) {
		this.additionalServicesDao = additionalServicesDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<AdditionalServicesDto>> getAll() {
		List<AdditionalServices> additionalServices = this.additionalServicesDao.findAll();
		List<AdditionalServicesDto> additionalServicesDtos = additionalServices.stream().map(additionalServ -> modelMapper.map(additionalServ, AdditionalServicesDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<>(additionalServicesDtos,AdditionalServiceMessages.GetAll);
	}

	@Override
	public Result add(CreateAdditionalServicesRequest createAdditionalServicesRequest) {

		var result = BusinessRules.run(checkAddionalServiceNameDuplication(createAdditionalServicesRequest.getAdditionalServiceName()));
		if (result != null) {
			return result;
		}

		AdditionalServices additionalServices=new AdditionalServices();
		additionalServices.setAdditionalServiceName(createAdditionalServicesRequest.getAdditionalServiceName());
		additionalServices.setAdditionalServicePrice(createAdditionalServicesRequest.getAdditionalServicePrice());

		this.additionalServicesDao.save(additionalServices);
		return new SuccessResult(true,AdditionalServiceMessages.Add);

	}

	@Override
	public DataResult<AdditionalServicesDto> getById(int id) {
		AdditionalServices additionalServices = this.additionalServicesDao.getById(id);
		AdditionalServicesDto additionalServicesDto = modelMapper.map(additionalServices, AdditionalServicesDto.class);
		return new SuccessDataResult<>(additionalServicesDto, AdditionalServiceMessages.GetById);
	}

	@Override
	public Result delete(DeleteAdditionalServicesRequest deleteAdditionalServicesRequest) {

		AdditionalServices additionalServices=new AdditionalServices();
		additionalServices.setId(deleteAdditionalServicesRequest.getId());

		this.additionalServicesDao.delete(additionalServices);

		return new SuccessResult(true,AdditionalServiceMessages.Delete);
	}

	@Override
	public Result update(UpdateAdditionalServicesRequest updateAdditionalServicesRequest) {

		AdditionalServices additionalServices=this.additionalServicesDao.getById(updateAdditionalServicesRequest.getId());
		additionalServices.setId(updateAdditionalServicesRequest.getId());
		additionalServices.setAdditionalServiceName(updateAdditionalServicesRequest.getAdditionalServiceName());
        additionalServices.setAdditionalServicePrice(updateAdditionalServicesRequest.getAdditionalServicePrice());

		this.additionalServicesDao.save(additionalServices);
		return new SuccessResult(true, AdditionalServiceMessages.Update);
	}


	//Aynı isimde hizmet var mı yok mu kontrolü
	private Result checkAddionalServiceNameDuplication(String additionalServiceName) {

		if (this.additionalServicesDao.existsAdditionalServicesByadditionalServiceName(additionalServiceName)) {
			return new ErrorResult(AdditionalServiceMessages.ErrorCheckAdditionalServiceName);
		}
		return new SuccessResult();
	}

}
