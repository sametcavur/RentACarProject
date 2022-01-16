package com.etiya.recap.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.recap.entities.concretes.Customer;

public interface CustomerDao  extends JpaRepository<Customer, Integer> {

}
