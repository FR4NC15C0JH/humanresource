package com.humanresource.rest.controller;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.humanresource.domain.model.Employees;
import com.humanresource.domain.model.dto.CurrentUserAuthenticationDto;
import com.humanresource.domain.service.EmployeesService;
import com.humanresource.security.jwt.model.JwtAuthenticationRequest;
import com.humanresource.security.jwt.util.JwtTokenUtil;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private EmployeesService employeesService;
	
	@PostMapping(value="/api/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
		
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest.getEmail(), 
						authenticationRequest.getPassword()
						)
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final Employees employees = employeesService.findByEmail(authenticationRequest.getEmail());
		employees.setPassword(null);
		return ResponseEntity.ok(new CurrentUserAuthenticationDto(token, employees));		
	}
	
	@PostMapping(value = "/api/refresh") 
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request){
		String token = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		final Employees employees = employeesService.findByEmail(username);
		
		if(jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refrehedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUserAuthenticationDto(refrehedToken, employees));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}

