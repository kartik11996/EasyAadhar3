package com.stackroute.authenticationservice.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.model.Role;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.RoleRepository;
import com.stackroute.authenticationservice.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Operator");
        adminRole.setRoleDescription("Operator role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

 
    }

    public User registerNewUser(String userName,String userPassword) throws UserAlreadyExistException {
    	if(userDao.findById(userName).isEmpty()) {
    	User user=new User();
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserName(userName);
        user.setUserPassword(getEncodedPassword(userPassword));

        return userDao.save(user);
    	}else {
    		throw new UserAlreadyExistException("user is already registered using this email ");
    		
    	}
    }
    
    public User registerNewOperator(String userName,String userPassword) throws UserAlreadyExistException {
    	if(userDao.findById(userName).isEmpty()) {
            User user=new User();
            Role role = roleDao.findById("Operator").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setUserName(userName);
            user.setUserPassword(getEncodedPassword(userPassword));

            return userDao.save(user);
        	}else {
        		throw new UserAlreadyExistException("user is already registered using this email ");
        		
        	}	}

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

	
}
