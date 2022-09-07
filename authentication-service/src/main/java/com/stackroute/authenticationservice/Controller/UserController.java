package com.stackroute.authenticationservice.Controller;

import com.stackroute.authenticationservice.Models.Users;
import com.stackroute.authenticationservice.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService Uservice;
    @Autowired
    public UserController(UserService uservice) {
        Uservice = uservice;
    }

    @PostMapping("/add")
    public void addUser(@RequestBody Users user){
        Uservice.saveUser(user);

    }


}
