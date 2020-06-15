package com.humanresource.domain.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.humanresource.domain.model.Departament;

@Component
public class DepartamentValidation {
	
	public void validateCreateDepartament(Departament departament, BindingResult result) {
		if(departament.getName() == null) {
			result.addError(new ObjectError("Departament","Name no information"));
			return;
		}
	}

}
