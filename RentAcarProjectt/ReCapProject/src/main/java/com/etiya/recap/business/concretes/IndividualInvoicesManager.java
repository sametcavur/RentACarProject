package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.IndividualInvoicesService;
import com.etiya.recap.business.constants.messages.IndividualInvoicesMessages;
import com.etiya.recap.core.services.queue.InvoiceService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.IndividualCustomerDao;
import com.etiya.recap.dataAccess.abstracts.IndividualInvoicesDao;
import com.etiya.recap.dataAccess.abstracts.RentalDao;
import com.etiya.recap.entities.concretes.ApplicationUser;
import com.etiya.recap.entities.concretes.IndividualCustomer;
import com.etiya.recap.entities.concretes.IndividualInvoices;
import com.etiya.recap.entities.concretes.Rental;
import com.etiya.recap.entities.dtos.IndividualCustomerInvoicesExampleDto;
import com.etiya.recap.entities.dtos.IndividualInvoicesDto;
import com.etiya.recap.entities.requests.invoiceRequests.CreateInvoiceBetweenDateRequest;
import com.etiya.recap.entities.requests.invoiceRequests.CreateInvoicesRequest;
import com.etiya.recap.entities.requests.invoiceRequests.DeleteInvoicesRequest;

@Service
public class IndividualInvoicesManager implements IndividualInvoicesService {

	private IndividualInvoicesDao individualInvoicesDao;
	private RentalDao rentalDao;
	private IndividualCustomerDao individualCustomerDao;
	private InvoiceService invoiceService;
	private final ModelMapper modelMapper;


	@Autowired
	public IndividualInvoicesManager( RentalDao rentalDao,
			IndividualCustomerDao individualCustomerDao,IndividualInvoicesDao individualInvoicesDao,InvoiceService invoiceService,ModelMapper modelMapper) {
		this.rentalDao = rentalDao;
		this.individualCustomerDao = individualCustomerDao;
		this.individualInvoicesDao=individualInvoicesDao;
		this.invoiceService=invoiceService;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<IndividualInvoicesDto>> getAll() {
		List<IndividualInvoices> individualInvoices = this.individualInvoicesDao.findAll();
		List<IndividualInvoicesDto> individualInvoicesDtos = individualInvoices.stream().map(individualCustomer -> modelMapper.map(individualCustomer, IndividualInvoicesDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualInvoicesDto>>(individualInvoicesDtos, IndividualInvoicesMessages.GetAll);
	}

	@Override
	public Result add(CreateInvoicesRequest createInvoicesRequest) {
		Rental rental = this.rentalDao.getById(createInvoicesRequest.getRentalId());		
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(rental.getUser().getUserId());
		
		IndividualCustomer individualCustomer =this.individualCustomerDao.getIndividualCustomerByUser(applicationUser);
		
		Random randomInvoiceNumber = new Random();
		
		IndividualInvoices individualInvoices = new IndividualInvoices();
		individualInvoices.setCreationDate(createInvoicesRequest.getCreationDate());
		individualInvoices.setInvoiceNumber(randomInvoiceNumber.nextInt((9999 - 1111) + 1) + 1111);
		individualInvoices.setRentDateCount(createInvoicesRequest.getTotalRentDateCount());
		individualInvoices.setRentPrice(createInvoicesRequest.getRentPrice());
		individualInvoices.setRental(this.rentalDao.getById(createInvoicesRequest.getRentalId()));
		individualInvoices.setIndividualCustomer(individualCustomer);

		this.individualInvoicesDao.save(individualInvoices);
		return new SuccessResult(true, IndividualInvoicesMessages.Add);
	}

	@Override
	public DataResult<IndividualInvoicesDto> getById(int id) {
		IndividualInvoices individualInvoices = this.individualInvoicesDao.getById(id);
		IndividualInvoicesDto individualInvoicesDto = modelMapper.map(individualInvoices, IndividualInvoicesDto.class);
		return new SuccessDataResult<IndividualInvoicesDto>(individualInvoicesDto, IndividualInvoicesMessages.GetById);
	}
	
	@Override
	public DataResult<IndividualCustomerInvoicesExampleDto> getIndividualInvoiceDtoByInvoiceId(int invoiceId) {
		
		IndividualInvoices individualInvoices=this.individualInvoicesDao.getById(invoiceId);
		IndividualCustomer individualCustomer=this.individualCustomerDao.getById(individualInvoices.getIndividualCustomer().getCustomerId());
		
		return new SuccessDataResult<IndividualCustomerInvoicesExampleDto>(this.invoiceService.individualCustomerInvoicesExampleDto(individualCustomer, individualInvoices),IndividualInvoicesMessages.GetIndividualInvoiceDtoByInvoiceId);
	}

	@Override
	public Result delete(DeleteInvoicesRequest deleteInvoicesRequest) {
		IndividualInvoices individualInvoices=new IndividualInvoices();
		individualInvoices.setId(deleteInvoicesRequest.getId());
		
		this.individualInvoicesDao.delete(individualInvoices);
		return new SuccessResult(true, IndividualInvoicesMessages.Delete);
	}

	@Override
	public DataResult<List<IndividualInvoicesDto>> getIndividualInvoicesByCustomerId(int customerId) {
		List<IndividualInvoices> individualInvoices = this.individualInvoicesDao.getIndividualInvoicesByCustomerId(customerId);
		List<IndividualInvoicesDto> individualInvoicesDtos = individualInvoices.stream().map(individualInvioces -> modelMapper.map(individualInvioces, IndividualInvoicesDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualInvoicesDto>>(individualInvoicesDtos, IndividualInvoicesMessages.GetIndividualInvoicesByCustomerId);
	}

	@Override
	public DataResult<List<IndividualInvoicesDto>> getIndividualInvoicesBetweenTwoDate(CreateInvoiceBetweenDateRequest createInvoiceBetweenDateRequest) {
		List<IndividualInvoices> individualInvoices = this.individualInvoicesDao.getByCreationDateBetween(createInvoiceBetweenDateRequest.getMinDate(), createInvoiceBetweenDateRequest.getMaxDate());
		List<IndividualInvoicesDto> individualInvoicesDtos = individualInvoices.stream().map(individualInvioces -> modelMapper.map(individualInvioces, IndividualInvoicesDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualInvoicesDto>>(individualInvoicesDtos, IndividualInvoicesMessages.GetIndividualInvoicesBetweenTwoDate);
	}
}
