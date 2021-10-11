package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.CorporateCustomerInvoiceExampleDto;
import com.etiya.recap.entities.dtos.CorporateInvoicesDto;
import com.etiya.recap.entities.requests.invoiceRequests.CreateInvoiceBetweenDateRequest;
import com.etiya.recap.entities.requests.invoiceRequests.CreateInvoicesRequest;
import com.etiya.recap.entities.requests.invoiceRequests.DeleteInvoicesRequest;

public interface CorporateInvoicesService {
	
	DataResult<List<CorporateInvoicesDto>> getAll();
	
	Result add(CreateInvoicesRequest createInvoicesRequest);
	
	DataResult<CorporateInvoicesDto> getById(int id);
	
	Result delete(DeleteInvoicesRequest deleteInvoicesRequest);
	

	DataResult<CorporateCustomerInvoiceExampleDto> getCorporateInvoiceDtoByInvoiceId(int invoiceId);
	
	DataResult <List<CorporateInvoicesDto>> getCorporateInvoicesByCustomerId(int customerId);
	
	DataResult<List<CorporateInvoicesDto>>getCorporateInvoicesBetweenTwoDate(CreateInvoiceBetweenDateRequest createInvoiceBetweenDateRequest);

	
	

}
