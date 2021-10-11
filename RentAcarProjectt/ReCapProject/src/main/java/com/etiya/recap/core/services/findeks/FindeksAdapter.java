package com.etiya.recap.core.services.findeks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.CustomerFindeksScoreCheckService;
import com.etiya.recap.entities.concretes.CorporateCustomer;
import com.etiya.recap.entities.concretes.IndividualCustomer;

@Service
public class FindeksAdapter implements CustomerFindeksScoreCheckService {

	private FindeksService findeksService;

	@Autowired
	public FindeksAdapter(FindeksService findeksService) {
		super();
		this.findeksService = findeksService;
	}

	@Override
	public int checkIndividualCustomerFindeksScore(IndividualCustomer individualCustomer) {
		return this.findeksService.checkIndividualCustomerFinsdeksScore(individualCustomer.getIdentityNumber());
	}

	@Override
	public int checkCorporateCustomerFindeksScore(CorporateCustomer corporateCustomer) {
		return this.findeksService.checkCorporateCustomerFinsdeksScore(corporateCustomer.getTaxNumber());
	}
	
}
