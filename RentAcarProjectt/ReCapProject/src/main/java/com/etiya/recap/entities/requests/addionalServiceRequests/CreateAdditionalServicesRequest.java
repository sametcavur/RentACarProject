package com.etiya.recap.entities.requests.addionalServiceRequests;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateAdditionalServicesRequest {
	
	@NotNull
    private String additionalServiceName;
	
	@NotNull
    private double additionalServicePrice;
	
	

}
