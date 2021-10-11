package com.etiya.recap.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.ApplicationUser;
@Repository
public interface ApplicationUserDao extends JpaRepository<ApplicationUser, Integer> {
	
	
	@Query("Select u.email From ApplicationUser u ")
	List<String> getEmails();
	
	boolean existsByEmail(String email);
	
	boolean existsByPassword(String password);
	
}
