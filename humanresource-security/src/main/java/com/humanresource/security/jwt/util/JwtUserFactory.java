package com.humanresource.security.jwt.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.humanresource.domain.model.Employees;
import com.humanresource.security.jwt.model.JwtUser;
/**
 * 
 * @author fr4nc15c0jh
 *
 */
public class JwtUserFactory {
	
	private JwtUserFactory() {
		
	}
	/**
	 * Converter um jwtUser com dados do User
	 */
	public static JwtUser create(Employees employees) {
		return new JwtUser(employees.getId(), employees.getEmail(), employees.getPassword(), mapToGrantedAuthorities(employees.getProfiles()));
	}
	/**
	 * Converter perfil User para ser usado no spring security
	 * @param profiles
	 * @return
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(String profiles) {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(profiles));
		
		return authorities;
	}
}
