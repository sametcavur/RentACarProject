package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.RentalDto;
import com.etiya.recap.entities.requests.rentalRequests.CreateDeliverTheCar;
import com.etiya.recap.entities.requests.rentalRequests.CreateRentalRequest;
import com.etiya.recap.entities.requests.rentalRequests.DeleteRentalRequest;
import com.etiya.recap.entities.requests.rentalRequests.UpdateRentalRequest;

public interface RentalService {
	
	DataResult<List<RentalDto>> getAll();
	
	DataResult<RentalDto> getById(int id);

	Result rentCorporateCustomer(CreateRentalRequest createRentalRequest);
	
	Result rentIndividualCustomer(CreateRentalRequest createRentalRequest);
	
	Result delete(DeleteRentalRequest deleteRentalRequest);
	
	Result updateIndividualCustomerRent(UpdateRentalRequest updateRentalRequest);
	
	Result updateCorporateCustomerRent(UpdateRentalRequest updateRentalRequest);
	
	Result deliverCorporateCustomerCar(CreateDeliverTheCar createDeliverTheCar);
	
	Result deliverIndividualCustomerCar(CreateDeliverTheCar createDeliverTheCar);
	

}
