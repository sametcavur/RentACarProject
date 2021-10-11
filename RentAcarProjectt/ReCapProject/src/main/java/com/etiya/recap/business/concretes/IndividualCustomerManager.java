package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.IndividualCustomerService;
import com.etiya.recap.business.constants.messages.IndividualCustomerMessages;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.recap.entities.concretes.ApplicationUser;
import com.etiya.recap.entities.concretes.IndividualCustomer;
import com.etiya.recap.entities.dtos.IndividualCustomerDto;
import com.etiya.recap.entities.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.etiya.recap.entities.requests.individualCustomerRequests.DeleteIndividualCustomerRequest;
import com.etiya.recap.entities.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	private IndividualCustomerDao individualCustomerDao;
	private final ModelMapper modelMapper;

	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,ModelMapper modelMapper) {
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapper = modelMapper;
	}
	@Override
	public DataResult<List<IndividualCustomerDto>> getAll() {
		List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAll();
		List<IndividualCustomerDto> individualCustomerDtos = individualCustomers.stream().map(individualCustomer -> modelMapper.map(individualCustomer, IndividualCustomerDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualCustomerDto>>(individualCustomerDtos, IndividualCustomerMessages.GetAll);
	}

	@Override
	public DataResult<IndividualCustomerDto> getById(int id) {
		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(id);
		IndividualCustomerDto individualCustomerDto = modelMapper.map(individualCustomer, IndividualCustomerDto.class);
		return new SuccessDataResult<IndividualCustomerDto>(individualCustomerDto, IndividualCustomerMessages.GetById);
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		ApplicationUser applicationUser=new ApplicationUser();
		applicationUser.setUserId(createIndividualCustomerRequest.getUserId());

		IndividualCustomer individualCustomer = new IndividualCustomer();
		individualCustomer.setApplicationUser(applicationUser);
		individualCustomer.setFirstName(createIndividualCustomerRequest.getFirstName());
		individualCustomer.setLastName(createIndividualCustomerRequest.getLastName());
		individualCustomer.setIdentityNumber(createIndividualCustomerRequest.getIdentityNumber());

		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(true, IndividualCustomerMessages.Add);
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = new IndividualCustomer();
		individualCustomer.setCustomerId(deleteIndividualCustomerRequest.getId());
		this.individualCustomerDao.delete(individualCustomer);
		return new SuccessResult(true, IndividualCustomerMessages.Delete);
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		ApplicationUser applicationUser=new ApplicationUser();
		applicationUser.setUserId(updateIndividualCustomerRequest.getUserId());

		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(updateIndividualCustomerRequest.getId());
		individualCustomer.setApplicationUser(applicationUser);
		individualCustomer.setCustomerId(updateIndividualCustomerRequest.getId());
		individualCustomer.setFirstName(updateIndividualCustomerRequest.getFirstName());
		individualCustomer.setLastName(updateIndividualCustomerRequest.getLastName());
		individualCustomer.setIdentityNumber(updateIndividualCustomerRequest.getIdentityNumber());

		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(true, IndividualCustomerMessages.Update);
	}

}


