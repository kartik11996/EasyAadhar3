package com.stackroute.authenticationservice.Repositories;

import com.stackroute.authenticationservice.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
}
