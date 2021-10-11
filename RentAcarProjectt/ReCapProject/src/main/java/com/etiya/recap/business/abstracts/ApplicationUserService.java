package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.dtos.ApplicationUserDto;
import com.etiya.recap.entities.requests.applicationUserRequests.CreateUserLoginRequest;
import com.etiya.recap.entities.requests.applicationUserRequests.CreateUserRegisterRequest;
import com.etiya.recap.entities.requests.applicationUserRequests.DeleteApplicationUserRequest;
import com.etiya.recap.entities.requests.applicationUserRequests.UpdateApplicationUserRequest;

public interface ApplicationUserService {
	
	
	DataResult<List<ApplicationUserDto>> getAll();
	
	DataResult<ApplicationUserDto> getById(int id);
	
	Result delete(DeleteApplicationUserRequest deleteApplicationUserRequest);
	
	Result update(UpdateApplicationUserRequest updateApplicationUserRequest);
	
	
	
	Result userLogin(CreateUserLoginRequest createUserLoginRequest);

	Result userRegister(CreateUserRegisterRequest createUserRegisterRequest);
	
}
