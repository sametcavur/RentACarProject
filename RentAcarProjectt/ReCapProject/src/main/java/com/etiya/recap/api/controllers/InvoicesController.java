package com.etiya.recap.api.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.recap.business.abstracts.CorporateInvoicesService;
import com.etiya.recap.business.abstracts.IndividualInvoicesService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CorporateCustomerInvoiceExampleDto;
import com.etiya.recap.entities.dtos.CorporateInvoicesDto;
import com.etiya.recap.entities.dtos.IndividualCustomerInvoicesExampleDto;
import com.etiya.recap.entities.dtos.IndividualInvoicesDto;
import com.etiya.recap.entities.requests.invoiceRequests.CreateInvoiceBetweenDateRequest;
import com.etiya.recap.entities.requests.invoiceRequests.DeleteInvoicesRequest;

@RestController
@RequestMapping("/api/corpareteinvoices")
public class InvoicesController {
	
	private CorporateInvoicesService corporateInvoicesService;
	private IndividualInvoicesService individualInvoicesService;

	@Autowired
	public InvoicesController(CorporateInvoicesService corporateInvoicesService,IndividualInvoicesService individualInvoicesService) {
		this.corporateInvoicesService = corporateInvoicesService;
		this.individualInvoicesService=individualInvoicesService;
	}

	@GetMapping("/getallcorporateinvoices")
	public DataResult<List<CorporateInvoicesDto>> getAllCorporateInvoices(){
		return this.corporateInvoicesService.getAll();
	}
	

	@GetMapping("/getallindividualinvoices")
	public DataResult<List<IndividualInvoicesDto>> getAllIndividualInvoices(){
		return this.individualInvoicesService.getAll();
	}
	
	@GetMapping("/getcorporateinvoicebyinvoiceid")
	public DataResult<CorporateInvoicesDto> getCorporateInvoicesByInvoiceId(int id) {
		return this.corporateInvoicesService.getById(id);
	}
	
	@GetMapping("/getindividualinvoicebyinvoiceid")
	public DataResult<IndividualInvoicesDto> getIndividualInvoicesByInvoiceId(int id) {
		return this.individualInvoicesService.getById(id);
	}
	
	@DeleteMapping("/removecorporateinvoice")
	public Result removeCorporateInvoice(DeleteInvoicesRequest deleteInvoicesRequest) {
		return this.corporateInvoicesService.delete(deleteInvoicesRequest);
	}
	
	@DeleteMapping("/removeindividualinvoice")
	public Result removeIndividualInvoice(DeleteInvoicesRequest deleteInvoicesRequest) {
		return this.individualInvoicesService.delete(deleteInvoicesRequest);
	}
	
	@GetMapping("/getindividualinvoicebyinvoiceidExample")
	public DataResult<IndividualCustomerInvoicesExampleDto> getIndividualInvoiceDtoByInvoiceId(int id){
		return this.individualInvoicesService.getIndividualInvoiceDtoByInvoiceId(id);
	}
	
	@GetMapping("/getcorporateinvoicebyinvoiceidExample")
	public DataResult<CorporateCustomerInvoiceExampleDto> getCorporateInvoiceDtoByInvoiceId(int id){
		return this.corporateInvoicesService.getCorporateInvoiceDtoByInvoiceId(id);
	}
	
	@GetMapping("/getindividualinvoicesbycustomerid")
	public DataResult<List<IndividualInvoicesDto>> getIndividualInvoicesByCustomerId(int customerId)
	{
		return this.individualInvoicesService.getIndividualInvoicesByCustomerId(customerId);
	}
	
	@GetMapping("/getcorporateinvoicesbycustomerid")
	public DataResult<List<CorporateInvoicesDto>> getCorporateInvoicesByCustomerId(int customerId)
	{
		return this.corporateInvoicesService.getCorporateInvoicesByCustomerId(customerId);
	}
	
	@GetMapping("/getindividualinvoicesbetweentwodate")
	public DataResult<List<IndividualInvoicesDto>> getIndividualInvoicesBetweenTwoDate(String minDate, String maxDate) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date minDate1 = dateFormat.parse(minDate);
		Date maxDate1 = dateFormat.parse(maxDate);

		CreateInvoiceBetweenDateRequest createInvoiceBetweenDateRequest = new CreateInvoiceBetweenDateRequest();
		createInvoiceBetweenDateRequest.setMinDate(minDate1);
		createInvoiceBetweenDateRequest.setMaxDate(maxDate1);
		
		return this.individualInvoicesService.getIndividualInvoicesBetweenTwoDate(createInvoiceBetweenDateRequest);
	}
	
	@GetMapping("/getcorporateinvoicesbetweentwodate")
	public DataResult<List<CorporateInvoicesDto>> getCorporateInvoicesBetweenTwoDate(String minDate, String maxDate) throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date minDate1 = dateFormat.parse(minDate);
		Date maxDate1 = dateFormat.parse(maxDate);

		CreateInvoiceBetweenDateRequest createInvoiceBetweenDateRequest = new CreateInvoiceBetweenDateRequest();
		createInvoiceBetweenDateRequest.setMinDate(minDate1);
		createInvoiceBetweenDateRequest.setMaxDate(maxDate1);
		
		return this.corporateInvoicesService.getCorporateInvoicesBetweenTwoDate(createInvoiceBetweenDateRequest);
	}

}
