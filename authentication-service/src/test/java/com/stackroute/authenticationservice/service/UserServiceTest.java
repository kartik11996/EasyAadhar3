package com.stackroute.authenticationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.model.Role;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.RoleRepository;
import com.stackroute.authenticationservice.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#initRoleAndUser()}
     */
    @Test
    void testInitRoleAndUser() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     UserService.passwordEncoder
        //     UserService.roleDao
        //     UserService.userDao

        Role role = new Role();
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");
        when(roleRepository.save((Role) any())).thenReturn(role);
        userService.initRoleAndUser();
    }

    /**
     * Method under test: {@link UserService#registerNewUser(String, String)}
     */
    @Test
    void testRegisterNewUser() throws UserAlreadyExistException {
        Role role = new Role();
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findById((String) any())).thenReturn(ofResult);

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");

        User user1 = new User();
        user1.setRole(new HashSet<>());
        user1.setUserName("janedoe");
        user1.setUserPassword("iloveyou");
        Optional<User> ofResult1 = Optional.of(user1);
        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findById((String) any())).thenReturn(ofResult1);
        assertThrows(UserAlreadyExistException.class, () -> userService.registerNewUser("janedoe", "iloveyou"));
        verify(userRepository).findById((String) any());
    }

    /**
     * Method under test: {@link UserService#registerNewUser(String, String)}
     */
    @Test
    void testRegisterNewUser2() throws UserAlreadyExistException {
        Role role = new Role();
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findById((String) any())).thenReturn(ofResult);

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findById((String) any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertSame(user, userService.registerNewUser("janedoe", "iloveyou"));
        verify(roleRepository).findById((String) any());
        verify(userRepository).save((User) any());
        verify(userRepository).findById((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#registerNewUser(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegisterNewUser3() throws UserAlreadyExistException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.stackroute.authenticationservice.service.UserService.registerNewUser(UserService.java:47)
        //   In order to prevent registerNewUser(String, String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   registerNewUser(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(roleRepository.findById((String) any())).thenReturn(Optional.empty());

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findById((String) any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        userService.registerNewUser("janedoe", "iloveyou");
    }

    /**
     * Method under test: {@link UserService#registerNewOperator(String, String)}
     */
    @Test
    void testRegisterNewOperator() throws UserAlreadyExistException {
        Role role = new Role();
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findById((String) any())).thenReturn(ofResult);

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");

        User user1 = new User();
        user1.setRole(new HashSet<>());
        user1.setUserName("janedoe");
        user1.setUserPassword("iloveyou");
        Optional<User> ofResult1 = Optional.of(user1);
        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findById((String) any())).thenReturn(ofResult1);
        assertThrows(UserAlreadyExistException.class, () -> userService.registerNewOperator("janedoe", "iloveyou"));
        verify(userRepository).findById((String) any());
    }

    /**
     * Method under test: {@link UserService#registerNewOperator(String, String)}
     */
    @Test
    void testRegisterNewOperator2() throws UserAlreadyExistException {
        Role role = new Role();
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findById((String) any())).thenReturn(ofResult);

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findById((String) any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertSame(user, userService.registerNewOperator("janedoe", "iloveyou"));
        verify(roleRepository).findById((String) any());
        verify(userRepository).save((User) any());
        verify(userRepository).findById((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#registerNewOperator(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegisterNewOperator3() throws UserAlreadyExistException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.stackroute.authenticationservice.service.UserService.registerNewOperator(UserService.java:64)
        //   In order to prevent registerNewOperator(String, String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   registerNewOperator(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(roleRepository.findById((String) any())).thenReturn(Optional.empty());

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findById((String) any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        userService.registerNewOperator("janedoe", "iloveyou");
    }

    /**
     * Method under test: {@link UserService#getEncodedPassword(String)}
     */
    @Test
    void testGetEncodedPassword() {
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertEquals("secret", userService.getEncodedPassword("iloveyou"));
        verify(passwordEncoder).encode((CharSequence) any());
    }
}

