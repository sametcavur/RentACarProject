package com.etiya.recap.dataAccess.abstracts;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.recap.entities.concretes.CorporateInvoices;

public interface CorporateInvoicesDao extends JpaRepository<CorporateInvoices, Integer>{
	
	@Query("From CorporateInvoices ci Where ci.corporateCustomer.customerId=:customerId ")
	List<CorporateInvoices> getCorporateInvoicesByCustomerId(int customerId);
	
	List<CorporateInvoices>getByCreationDateBetween(Date minDate,Date maxDate);


}
