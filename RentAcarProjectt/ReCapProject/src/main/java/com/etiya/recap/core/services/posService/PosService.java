package com.etiya.recap.core.services.posService;

import org.springframework.stereotype.Service;

import com.etiya.recap.entities.requests.creditCardRequests.CreatePosServiceRequest;

@Service
public interface PosService {
	
	public boolean withdraw(CreatePosServiceRequest createPosServiceRequest);

}
