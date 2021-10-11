package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.concretes.CreditCard;
import com.etiya.recap.entities.concretes.Rental;
import com.etiya.recap.entities.dtos.CreditCardDetailDto;
import com.etiya.recap.entities.requests.creditCardRequests.CreateCreditCardRequest;
import com.etiya.recap.entities.requests.creditCardRequests.DeleteCreditCardRequest;
import com.etiya.recap.entities.requests.creditCardRequests.UpdateCreditCardRequest;

public interface CreditCardService {
	
    DataResult<List<CreditCardDetailDto>> getAll();
    
    DataResult<CreditCardDetailDto> getById(int id);
	
	Result add(CreateCreditCardRequest createCreditCardRequest);
	
	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);
	
	Result update(UpdateCreditCardRequest updateCreditCardRequest);
	
	public Result checkCreditCardNumber(CreditCard creditCard);
	
	Result checkCreditCardLimit(CreditCard creditCard, double rentPrice,Rental rental);
	
}
