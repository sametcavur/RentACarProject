package com.etiya.recap.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.ApplicationUser;
import com.etiya.recap.entities.concretes.IndividualCustomer;

@Repository
public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer> {
	
	@Query("FROM IndividualCustomer ic WHERE ic.applicationUser=:applicationUser")
	IndividualCustomer getIndividualCustomerByUser(ApplicationUser applicationUser);

}
