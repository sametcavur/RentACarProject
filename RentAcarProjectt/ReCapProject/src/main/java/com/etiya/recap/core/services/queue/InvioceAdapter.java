package com.etiya.recap.core.services.queue;

import org.springframework.stereotype.Service;

import com.etiya.recap.entities.concretes.CorporateCustomer;
import com.etiya.recap.entities.concretes.IndividualCustomer;
import com.etiya.recap.entities.concretes.IndividualInvoices;
import com.etiya.recap.entities.dtos.CorporateCustomerInvoiceExampleDto;
import com.etiya.recap.entities.dtos.IndividualCustomerInvoicesExampleDto;
import com.etiya.recap.entities.concretes.CorporateInvoices;

@Service
public class InvioceAdapter implements InvoiceService{
	
	public CorporateCustomerInvoiceExampleDto invoiceCorporateCustomer(CorporateCustomer corporateCustomer,CorporateInvoices corporateInvoices) {
		CorporateCustomerInvoiceExampleDto corporateCustomerInvoiceExampleDto = new CorporateCustomerInvoiceExampleDto();
		
		corporateCustomerInvoiceExampleDto.setCompanyName(corporateCustomer.getCompanyName());
		corporateCustomerInvoiceExampleDto.setTaxNumber(corporateCustomer.getTaxNumber());
		corporateCustomerInvoiceExampleDto.setCreationDate(corporateInvoices.getCreationDate());
		corporateCustomerInvoiceExampleDto.setInvoiceNumber(corporateInvoices.getInvoiceNumber());
		corporateCustomerInvoiceExampleDto.setRentDateCount(corporateInvoices.getRentDateCount());
		corporateCustomerInvoiceExampleDto.setRentPrice(corporateInvoices.getRentPrice());
		return corporateCustomerInvoiceExampleDto;
	}

	public IndividualCustomerInvoicesExampleDto individualCustomerInvoicesExampleDto(IndividualCustomer individualCustomer,IndividualInvoices individualInvoices) {
		IndividualCustomerInvoicesExampleDto individualCustomerInvoicesExampleDto = new IndividualCustomerInvoicesExampleDto();
		individualCustomerInvoicesExampleDto.setFirstName(individualCustomer.getFirstName());
		individualCustomerInvoicesExampleDto.setLastName(individualCustomer.getLastName());
		individualCustomerInvoicesExampleDto.setIdentityNumber(individualCustomer.getIdentityNumber());
		individualCustomerInvoicesExampleDto.setInvoiceNumber(individualInvoices.getInvoiceNumber());
		individualCustomerInvoicesExampleDto.setRentDateCount(individualInvoices.getRentDateCount());
		individualCustomerInvoicesExampleDto.setRentPrice(individualInvoices.getRentPrice());
		individualCustomerInvoicesExampleDto.setCreationDate(individualInvoices.getCreationDate());
		
		return individualCustomerInvoicesExampleDto;
	}


}
