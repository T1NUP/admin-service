package com.cts.microservice.adminreport.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.microservice.adminreport.model.JwtAuthCredentials;
import com.cts.microservice.adminreport.model.JwtTokenResponse;
import com.cts.microservice.adminreport.service.UserValidationService;
import com.cts.microservice.adminreport.util.JwtUtil;


@RestController
@CrossOrigin
public class AuthentationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserValidationService userValidationService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthCredentials authenticationRequest)
			throws Exception {

		Objects.requireNonNull(authenticationRequest.getUsername());
		Objects.requireNonNull(authenticationRequest.getPassword());

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
//			throw new AuthenticationException("USER_DISABLED", e);
			System.out.println("USER_DISABLED");
		} catch (BadCredentialsException e) {
//			throw new AuthenticationException("INVALID_CREDENTIALS", e);
//			System.out.println("INVALID_CREDENTIALS");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		
		final UserDetails userDetails = userValidationService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtTokenResponse(token));
	}
}
