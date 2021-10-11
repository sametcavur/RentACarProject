package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.IndividualCustomerInvoicesExampleDto;
import com.etiya.recap.entities.dtos.IndividualInvoicesDto;
import com.etiya.recap.entities.requests.invoiceRequests.CreateInvoiceBetweenDateRequest;
import com.etiya.recap.entities.requests.invoiceRequests.CreateInvoicesRequest;
import com.etiya.recap.entities.requests.invoiceRequests.DeleteInvoicesRequest;

public interface IndividualInvoicesService {
	
	DataResult<List<IndividualInvoicesDto>> getAll();
	
	Result add(CreateInvoicesRequest createInvoicesRequest);
	
	DataResult<IndividualInvoicesDto> getById(int id);
	
	Result delete(DeleteInvoicesRequest deleteInvoicesRequest);
	

	DataResult<IndividualCustomerInvoicesExampleDto> getIndividualInvoiceDtoByInvoiceId(int invoiceId);
	
	DataResult<List<IndividualInvoicesDto>> getIndividualInvoicesByCustomerId(int customerId);
	
	DataResult<List<IndividualInvoicesDto>>getIndividualInvoicesBetweenTwoDate(CreateInvoiceBetweenDateRequest createInvoiceBetweenDateRequest);

	
	
	

}
