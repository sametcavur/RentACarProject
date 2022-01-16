package com.etiya.recap.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.recap.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
