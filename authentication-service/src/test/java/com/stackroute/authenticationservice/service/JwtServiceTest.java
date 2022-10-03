package com.stackroute.authenticationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.authenticationservice.exception.InvalidCredentialException;
import com.stackroute.authenticationservice.model.JwtRequest;
import com.stackroute.authenticationservice.model.JwtResponse;
import com.stackroute.authenticationservice.model.Role;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.util.JwtUtil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtService.class})
@ExtendWith(SpringExtension.class)
class JwtServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserRepository userRepository;


    @Test
    void testCreateJwtToken() throws InvalidCredentialException, AuthenticationException {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("jwt-token");

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("ayaz@gmail.com");
        user.setUserPassword("Ayaz@1234");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById((String) any())).thenReturn(ofResult);
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        ResponseEntity<?> actualCreateJwtTokenResult = jwtService.createJwtToken(new JwtRequest("ayaz@gmail.com", "Ayaz@1234"));
        assertTrue(actualCreateJwtTokenResult.hasBody());
        assertTrue(actualCreateJwtTokenResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCreateJwtTokenResult.getStatusCode());
        assertEquals("jwt-token", ((JwtResponse) actualCreateJwtTokenResult.getBody()).getJwtToken());
        assertSame(user, ((JwtResponse) actualCreateJwtTokenResult.getBody()).getUser());
        verify(jwtUtil).generateToken((UserDetails) any());
        verify(userRepository, atLeast(1)).findById((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }



    @Test
    void testCreateJwtToken2() throws InvalidCredentialException, AuthenticationException {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("jwt-token");

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("ayaz@gmail.com");
        user.setUserPassword("Ayaz@1234");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById((String) any())).thenReturn(ofResult);
        when(authenticationManager.authenticate((Authentication) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(InvalidCredentialException.class,
                () -> jwtService.createJwtToken(new JwtRequest("ayaz@gmail.com", "Ayaz@1234")));
        verify(authenticationManager).authenticate((Authentication) any());
    }


    @Test
    void testCreateJwtToken3() throws InvalidCredentialException, AuthenticationException {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("jwt-token");

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("ayaz@gmail.com");
        user.setUserPassword("Ayaz@1234");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById((String) any())).thenReturn(ofResult);
        when(authenticationManager.authenticate((Authentication) any())).thenThrow(new DisabledException("Msg"));
        assertThrows(InvalidCredentialException.class,
                () -> jwtService.createJwtToken(new JwtRequest("ayaz@gmail.com", "Ayaz@1234")));
        verify(authenticationManager).authenticate((Authentication) any());
    }


    @Test
    void testCreateJwtToken4() throws InvalidCredentialException, AuthenticationException {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("jwt-token");

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserName("ayaz@gmail.com");
        user.setUserPassword("Ayaz@1234");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById((String) any())).thenReturn(ofResult);
        when(authenticationManager.authenticate((Authentication) any())).thenThrow(new BadCredentialsException("Msg"));
        assertThrows(InvalidCredentialException.class,
                () -> jwtService.createJwtToken(new JwtRequest("ayaz@gmail.com", "Ayaz@1234")));
        verify(authenticationManager).authenticate((Authentication) any());
    }


    @Test
    void testCreateJwtToken5() throws InvalidCredentialException, AuthenticationException {
        when(jwtUtil.generateToken((UserDetails) any())).thenReturn("jwt-token");
        User user = mock(User.class);
        when(user.getUserName()).thenReturn("ayaz@gmail.com");
        when(user.getUserPassword()).thenReturn("Ayaz@1234");
        when(user.getRole()).thenReturn(new HashSet<>());
        doNothing().when(user).setRole((Set<Role>) any());
        doNothing().when(user).setUserName((String) any());
        doNothing().when(user).setUserPassword((String) any());
        user.setRole(new HashSet<>());
        user.setUserName("ayaz@gmail.com");
        user.setUserPassword("Ayaz@1234");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById((String) any())).thenReturn(ofResult);
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        ResponseEntity<?> actualCreateJwtTokenResult = jwtService.createJwtToken(new JwtRequest("ayaz@gmail.com", "Ayaz@1234"));
        assertTrue(actualCreateJwtTokenResult.hasBody());
        assertTrue(actualCreateJwtTokenResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCreateJwtTokenResult.getStatusCode());
        assertEquals("jwt-token", ((JwtResponse) actualCreateJwtTokenResult.getBody()).getJwtToken());
        verify(jwtUtil).generateToken((UserDetails) any());
        verify(userRepository, atLeast(1)).findById((String) any());
        verify(user).getUserName();
        verify(user).getUserPassword();
        verify(user).getRole();
        verify(user).setRole((Set<Role>) any());
        verify(user).setUserName((String) any());
        verify(user).setUserPassword((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }


}

