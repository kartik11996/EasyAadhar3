package com.stackroute.authenticationservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.stackroute.authenticationservice.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}
