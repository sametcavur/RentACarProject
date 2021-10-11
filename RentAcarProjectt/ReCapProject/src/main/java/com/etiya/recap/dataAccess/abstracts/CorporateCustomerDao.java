package com.etiya.recap.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.ApplicationUser;
import com.etiya.recap.entities.concretes.CorporateCustomer;
@Repository
public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer>{
	
	
	@Query("FROM CorporateCustomer cc WHERE cc.applicationUser=:applicationUser")
	CorporateCustomer getCorporateCustomerByUser(ApplicationUser applicationUser);

}
