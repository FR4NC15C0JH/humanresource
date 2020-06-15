package com.humanresource.domain.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.humanresource.domain.model.Job;

@Component
public class JobValidation {
	
	public void validateCreateJob(Job job, BindingResult result) {
		if(job.getName() == null) {
			result.addError(new ObjectError("Job","Name no information!"));
			return;
		}
	}
}
