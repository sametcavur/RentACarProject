package com.etiya.recap.business.abstracts;

import java.util.List;

import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.concretes.User;

public interface UserService {
	
	DataResult<List<User>> getAll();

	Result add(User user);
	
	DataResult<User> getById(int id);
	
	Result delete(User user);
	
	Result update(User user);
	
	

}
