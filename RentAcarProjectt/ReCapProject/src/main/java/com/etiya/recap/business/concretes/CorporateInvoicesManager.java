package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.CorporateInvoicesService;
import com.etiya.recap.business.constants.messages.CorporateInvoicesMessages;
import com.etiya.recap.core.services.queue.InvoiceService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.CorporateCustomerDao;
import com.etiya.recap.dataAccess.abstracts.CorporateInvoicesDao;
import com.etiya.recap.dataAccess.abstracts.RentalDao;
import com.etiya.recap.entities.concretes.ApplicationUser;
import com.etiya.recap.entities.concretes.CorporateCustomer;
import com.etiya.recap.entities.concretes.CorporateInvoices;
import com.etiya.recap.entities.concretes.Rental;
import com.etiya.recap.entities.dtos.CorporateCustomerInvoiceExampleDto;
import com.etiya.recap.entities.dtos.CorporateInvoicesDto;
import com.etiya.recap.entities.requests.invoiceRequests.CreateInvoiceBetweenDateRequest;
import com.etiya.recap.entities.requests.invoiceRequests.CreateInvoicesRequest;
import com.etiya.recap.entities.requests.invoiceRequests.DeleteInvoicesRequest;

@Service
public class CorporateInvoicesManager implements CorporateInvoicesService {

	private CorporateInvoicesDao corporateInvoicesDao;
	private RentalDao rentalDao;
	private CorporateCustomerDao corporateCustomerDao;
	private InvoiceService invoiceService;
	private final ModelMapper modelMapper;

	@Autowired
	public CorporateInvoicesManager(CorporateInvoicesDao corporateInvoicesDao, RentalDao rentalDao,CorporateCustomerDao corporateCustomerDao
			,InvoiceService invoiceService,ModelMapper modelMapper) {
		this.corporateInvoicesDao = corporateInvoicesDao;
		this.rentalDao = rentalDao;
		this.corporateCustomerDao = corporateCustomerDao;
		this.invoiceService=invoiceService;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<CorporateInvoicesDto>> getAll() {
		List<CorporateInvoices> corporateInvoices = this.corporateInvoicesDao.findAll();
		List<CorporateInvoicesDto> corporateInvoicesDtos = corporateInvoices.stream().map(corporate -> modelMapper.map(corporate, CorporateInvoicesDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateInvoicesDto>>(corporateInvoicesDtos, CorporateInvoicesMessages.GetAll);
	}

	@Override
	public Result add(CreateInvoicesRequest createInvoicesRequest) {
		Rental rental = this.rentalDao.getById(createInvoicesRequest.getRentalId());		
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(rental.getUser().getUserId());
		
		CorporateCustomer corporateCustomer=this.corporateCustomerDao.getCorporateCustomerByUser(applicationUser);
		
		Random randomInvoiceNumber = new Random();
		
		CorporateInvoices corporateInvoices = new CorporateInvoices();
		corporateInvoices.setCreationDate(createInvoicesRequest.getCreationDate());
		corporateInvoices.setInvoiceNumber(randomInvoiceNumber.nextInt((9999 - 1111) + 1) + 1111);
		corporateInvoices.setRentDateCount(createInvoicesRequest.getTotalRentDateCount());
		corporateInvoices.setRentPrice(createInvoicesRequest.getRentPrice());
		corporateInvoices.setRental(this.rentalDao.getById(createInvoicesRequest.getRentalId()));
		corporateInvoices.setCorporateCustomer(corporateCustomer);

		this.corporateInvoicesDao.save(corporateInvoices);
		return new SuccessResult(true, CorporateInvoicesMessages.Add);
	}

	@Override
	public DataResult<CorporateInvoicesDto> getById(int id) {
		CorporateInvoices corporateInvoices = this.corporateInvoicesDao.getById(id);
		CorporateInvoicesDto corporateInvoicesDto = modelMapper.map(corporateInvoices, CorporateInvoicesDto.class);
		return new SuccessDataResult<CorporateInvoicesDto>(corporateInvoicesDto, CorporateInvoicesMessages.GetById);
	}
	
	@Override
	public DataResult<CorporateCustomerInvoiceExampleDto> getCorporateInvoiceDtoByInvoiceId(int invoiceId) {
		CorporateInvoices corporateInvoices = this.corporateInvoicesDao.getById(invoiceId);
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(corporateInvoices.getCorporateCustomer().getCustomerId());
		
		return new SuccessDataResult<CorporateCustomerInvoiceExampleDto>(this.invoiceService.invoiceCorporateCustomer(corporateCustomer, corporateInvoices), CorporateInvoicesMessages.GetCorporateInvoiceDtoByInvoiceId);
	}
	

	@Override
	public Result delete(DeleteInvoicesRequest deleteInvoicesRequest) {
		CorporateInvoices corporateInvoices = new CorporateInvoices();
		corporateInvoices.setId(deleteInvoicesRequest.getId());
		this.corporateInvoicesDao.delete(corporateInvoices);
		return new SuccessResult(true, CorporateInvoicesMessages.Delete);
	}

	@Override
	public DataResult<List<CorporateInvoicesDto>> getCorporateInvoicesByCustomerId(int customerId) {
		List<CorporateInvoices> corporateInvoices = this.corporateInvoicesDao.getCorporateInvoicesByCustomerId(customerId);
		List<CorporateInvoicesDto> corporateInvoicesDtos = corporateInvoices.stream().map(corporate -> modelMapper.map(corporate, CorporateInvoicesDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateInvoicesDto>>(corporateInvoicesDtos, CorporateInvoicesMessages.GetCorporateInvoicesByCustomerId);
	}
	
	@Override
	public DataResult<List<CorporateInvoicesDto>> getCorporateInvoicesBetweenTwoDate(CreateInvoiceBetweenDateRequest createInvoiceBetweenDateRequest) {
		List<CorporateInvoices> corporateInvoices = this.corporateInvoicesDao.getByCreationDateBetween(createInvoiceBetweenDateRequest.getMinDate(),createInvoiceBetweenDateRequest.getMaxDate());
		List<CorporateInvoicesDto> corporateInvoicesDtos = corporateInvoices.stream().map(corporate -> modelMapper.map(corporate, CorporateInvoicesDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateInvoicesDto>>(corporateInvoicesDtos, CorporateInvoicesMessages.GetCorporateInvoicesBetweenTwoDate);
		}
	}


