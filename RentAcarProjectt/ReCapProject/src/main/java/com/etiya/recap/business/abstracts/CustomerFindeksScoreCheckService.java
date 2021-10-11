package com.etiya.recap.business.abstracts;

import com.etiya.recap.entities.concretes.CorporateCustomer;
import com.etiya.recap.entities.concretes.IndividualCustomer;

public interface CustomerFindeksScoreCheckService {
	
	int checkIndividualCustomerFindeksScore(IndividualCustomer individualCustomer);
	
	int checkCorporateCustomerFindeksScore(CorporateCustomer corporateCustomer);
}
