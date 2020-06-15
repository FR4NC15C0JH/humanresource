package com.humanresource.security.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.humanresource.domain.model.Employees;
import com.humanresource.domain.service.EmployeesService;
import com.humanresource.security.jwt.util.JwtUserFactory;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
	private EmployeesService employeesService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employees user = employeesService.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
		}else {			
			return JwtUserFactory.create(user);
		}
	}	
}
