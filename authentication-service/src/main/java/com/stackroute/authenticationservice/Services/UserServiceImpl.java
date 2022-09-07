package com.stackroute.authenticationservice.Services;

import com.stackroute.authenticationservice.Models.Users;
import com.stackroute.authenticationservice.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserRepository URepo;

    @Override
    public void saveUser(Users user) {


        URepo.save(user);
    }
}
