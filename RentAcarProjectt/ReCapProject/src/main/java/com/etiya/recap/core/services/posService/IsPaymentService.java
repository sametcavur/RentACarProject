package com.etiya.recap.core.services.posService;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class IsPaymentService {

	public boolean withdraw(String nameOnTheCard,String cardNumber,Date expirationDate, String cvc,double feePayable) {
		double limit = 2000;
		if(limit >= feePayable) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
