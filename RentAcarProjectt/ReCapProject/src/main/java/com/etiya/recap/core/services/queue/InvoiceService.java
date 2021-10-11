package com.etiya.recap.core.services.queue;

import org.springframework.stereotype.Service;

import com.etiya.recap.entities.concretes.CorporateCustomer;
import com.etiya.recap.entities.concretes.IndividualCustomer;
import com.etiya.recap.entities.concretes.IndividualInvoices;
import com.etiya.recap.entities.dtos.CorporateCustomerInvoiceExampleDto;
import com.etiya.recap.entities.dtos.IndividualCustomerInvoicesExampleDto;
import com.etiya.recap.entities.concretes.CorporateInvoices;

@Service
public interface InvoiceService {

	public CorporateCustomerInvoiceExampleDto invoiceCorporateCustomer(CorporateCustomer corporateCustomer,CorporateInvoices corporateInvoices);

	public IndividualCustomerInvoicesExampleDto individualCustomerInvoicesExampleDto(IndividualCustomer individualCustomer,IndividualInvoices individualInvoices);

}
