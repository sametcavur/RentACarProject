package com.etiya.recap.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.recap.business.abstracts.UserService;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.entities.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	@PostMapping("/adduser")
	public Result addUser(User user) {
		return this.userService.add(user);
	}

	@GetMapping("/getallusers")
	public DataResult<List<User>> getAllUsers() {
		return this.userService.getAll();
	}
	
	@GetMapping("/getuserbyid")
	public  DataResult<User> getUserById(int id) {
		return this.userService.getById(id);
	}
	
	
	@DeleteMapping("/removeuser")
	public Result removeCar(User user) {
		return this.userService.delete(user);
	}
	
	@PostMapping("/updateuser")
	public Result updateCar(User user) {
		return this.userService.update(user);
	}

}
