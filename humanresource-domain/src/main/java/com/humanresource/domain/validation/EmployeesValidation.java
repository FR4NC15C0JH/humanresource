package com.humanresource.domain.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.humanresource.domain.model.Employees;
import com.humanresource.domain.service.EmployeesService;

@Component
public class EmployeesValidation {
	
	@Autowired
	private EmployeesService employeesService;
	
	public void validateEmail(Employees employees, BindingResult result) {
		if(employees.getEmail() == null) {
			result.addError(new ObjectError("Employees","Email not information!"));
			return;
		}
	}
	
	public void validateEmployees(Employees employees, BindingResult result) {
		Employees employeesSearch = employeesService.findByEmail(employees.getEmail());
		if(employeesSearch != null) {
			result.addError(new ObjectError("Employees", "Employees cadastred!"));
			return;
		}
	}
	
	public void validateSave(Employees user,BindingResult result) {
		this.validateEmail(user, result);
		this.validateEmployees(user, result);
	}
}
