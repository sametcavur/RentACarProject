package com.etiya.recap.core.services.posService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.entities.requests.creditCardRequests.CreatePosServiceRequest;

@Service
public class PosServiceAdapter implements PosService {
	
	@Autowired
	IsPaymentService isPaymentService;

	@Override
	public boolean withdraw(CreatePosServiceRequest createPosServiceRequest) {
		return this.isPaymentService.withdraw(
				createPosServiceRequest.getNameOnTheCard(),
				createPosServiceRequest.getCardNumber(), 
				createPosServiceRequest.getExpirationDate(),
				createPosServiceRequest.getCvc(), 
				createPosServiceRequest.getFeePayable());
	}

}
