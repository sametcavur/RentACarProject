package com.etiya.recap.entities.requests.addionalServiceRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdditionalServicesRequest {
	
	private int id;
	
    private String additionalServiceName;
	
    private double additionalServicePrice;
	
	

}
