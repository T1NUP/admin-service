package com.cts.microservice.adminreport.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService implements UserDetailsService {
	
	@Value("${admin.username}")
	private String username;
	
	@Value("${admin.password}")
	private String password;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return new User(username, password ,new ArrayList<>());
	}
}
