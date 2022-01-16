package com.etiya.recap.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.recap.entities.concretes.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer> {

}
