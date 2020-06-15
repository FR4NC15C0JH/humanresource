package com.humanresource.domain.model.dto;

import com.humanresource.domain.model.Employees;

public class CurrentUserAuthenticationDto {
	
	private String token;
	private Employees employees;
	
	public CurrentUserAuthenticationDto(String token, Employees Employees) {
		super();
		this.token = token;
		this.employees = Employees;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Employees getEmployees() {
		return employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}
}
