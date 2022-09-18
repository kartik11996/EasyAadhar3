package com.stackroute.authenticationservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.stackroute.authenticationservice.model.Role;

public interface RoleRepository extends CrudRepository<Role, String>{

}
