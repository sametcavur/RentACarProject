package com.etiya.recap.dataAccess.abstracts;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.etiya.recap.entities.concretes.IndividualInvoices;

public interface IndividualInvoicesDao extends JpaRepository<IndividualInvoices, Integer> {
	
	
	@Query("From IndividualInvoices ii Where ii.individualCustomer.customerId=:customerId ")
	List<IndividualInvoices> getIndividualInvoicesByCustomerId(int customerId);
	
	List<IndividualInvoices>getByCreationDateBetween(Date minDate,Date maxDate);

}
