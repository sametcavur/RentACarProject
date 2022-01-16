package com.etiya.recap.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.ApplicationUserService;
import com.etiya.recap.business.constants.messages.UserMessages;
import com.etiya.recap.core.utilities.business.BusinessRules;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.ErrorResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.ApplicationUserDao;
import com.etiya.recap.entities.concretes.ApplicationUser;
import com.etiya.recap.entities.dtos.ApplicationUserDto;
import com.etiya.recap.entities.requests.applicationUserRequests.CreateUserLoginRequest;
import com.etiya.recap.entities.requests.applicationUserRequests.CreateUserRegisterRequest;
import com.etiya.recap.entities.requests.applicationUserRequests.DeleteApplicationUserRequest;
import com.etiya.recap.entities.requests.applicationUserRequests.UpdateApplicationUserRequest;

@Service
public class ApplicationUserManager implements ApplicationUserService {

	private ApplicationUserDao applicationUserDao;
	private final ModelMapper modelMapper;

	@Autowired
	public ApplicationUserManager(ApplicationUserDao applicationUserDao,ModelMapper modelMapper) {
		this.applicationUserDao = applicationUserDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<ApplicationUserDto>> getAll() {
		List<ApplicationUser> applicationUsers = this.applicationUserDao.findAll();
		List<ApplicationUserDto> applicationUserDtos = applicationUsers.stream().map(user -> modelMapper.map(user, ApplicationUserDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<>(applicationUserDtos, UserMessages.GetAll);
	}

	@Override
	public DataResult<ApplicationUserDto> getById(int id) {

		ApplicationUser applicationUser= this.applicationUserDao.getById(id);
		ApplicationUserDto applicationUserDto = modelMapper.map(applicationUser, ApplicationUserDto.class);
		return new SuccessDataResult<>(applicationUserDto, UserMessages.GetById);

	}

	@Override
	public Result delete(DeleteApplicationUserRequest deleteApplicationUserRequest) {

		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(deleteApplicationUserRequest.getId());

		this.applicationUserDao.delete(applicationUser);
		return new SuccessResult(true, UserMessages.Delete);
	}

	@Override
	public Result update(UpdateApplicationUserRequest updateApplicationUserRequest) {

		ApplicationUser applicationUser = this.applicationUserDao.getById(updateApplicationUserRequest.getId());
		applicationUser.setUserId(updateApplicationUserRequest.getId());
		applicationUser.setEmail(updateApplicationUserRequest.getEmail());
		applicationUser.setPassword(updateApplicationUserRequest.getPassword());

		this.applicationUserDao.save(applicationUser);
		return new SuccessResult(true, UserMessages.Update);
	}

	@Override
	public Result userLogin(CreateUserLoginRequest createUserLoginRequest) {

		ApplicationUser applicationUser = new ApplicationUser();

		applicationUser.setEmail(createUserLoginRequest.getEmail());
		applicationUser.setPassword(createUserLoginRequest.getPassword());

		var result = BusinessRules.run(checkEmailAndPassForLogin(applicationUser));

		if (result != null) {
			return result;
		}
		return new SuccessResult(true, UserMessages.SuccessLogin);
	}

	@Override
	public Result userRegister(CreateUserRegisterRequest createUserRegisterRequest) {

		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setEmail(createUserRegisterRequest.getEmail());
		applicationUser.setPassword(createUserRegisterRequest.getPassword());

		var result = BusinessRules.run(checkEmailForRegister(applicationUser));

		if (result != null) {
			return result;
		}

		this.applicationUserDao.save(applicationUser);
		return new SuccessResult(true, UserMessages.SuccessRegister);
	}

	// Kayıt olurken aynı email daha önce kullanılmış mı
	private Result checkEmailForRegister(ApplicationUser applicationUser) {
			if(this.applicationUserDao.existsByEmail(applicationUser.getEmail())) {
				return new ErrorResult(UserMessages.ErrorMailIsAlreadyExist);
			}
			return new SuccessResult();
		}

	// Giriş yaparken email ve şifre dbde var mı
	private Result checkEmailAndPassForLogin(ApplicationUser applicationUser) {
		if(!this.applicationUserDao.existsByEmail(applicationUser.getEmail()) ||
				!this.applicationUserDao.existsByPassword(applicationUser.getPassword())){
			return new ErrorResult(UserMessages.ErrorLogin);
		}
		return new SuccessResult();
	}

}
