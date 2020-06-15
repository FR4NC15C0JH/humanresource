package com.humanresource.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humanresource.domain.enumerator.Profiles;
import com.humanresource.domain.model.Employees;
import com.humanresource.domain.model.dto.ResponseDto;
import com.humanresource.domain.service.EmployeesService;
import com.humanresource.domain.validation.EmployeesValidation;

/**
 * 
 * @author fr4nc15c0jh
 *
 */
@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeesController {
	
	@Autowired
	private EmployeesService employeesService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmployeesValidation employeesValidation;
	
	@PostMapping
	@RequestMapping("/create")
	public ResponseEntity<ResponseDto<Employees>> create(HttpServletRequest request, @RequestBody Employees employees, BindingResult result){
		ResponseDto<Employees> response = new ResponseDto<>();
		List<String> errors = new ArrayList<>();
		try {
			employeesValidation.validateSave(employees, result);
			if(result.hasErrors()) {
				for(ObjectError error : result.getAllErrors()) {
					errors.add(error.getDefaultMessage());
				}
				response.setErrors(errors);
				return ResponseEntity.badRequest().body(response);
			}
			employees.setPassword(passwordEncoder.encode(employees.getPassword()));
			if(employees.getProfiles() == null) {
				employees.setProfiles(Profiles.ROLE_CUSTOMER.getDescricao());
			}
			Employees employeesPersisted = (Employees) employeesService.save(employees);
			response.setData(employeesPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<ResponseDto<Employees>> update(HttpServletRequest request, @RequestBody Employees employees, BindingResult result){
		ResponseDto<Employees> response = new ResponseDto<>();
		try {
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			employees.setPassword(passwordEncoder.encode(employees.getPassword()));
			Employees userUpdate = (Employees) employeesService.save(employees);
			response.setData(userUpdate);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<ResponseDto<Employees>> findById(@PathVariable("id") Long id){
		ResponseDto<Employees> response = new ResponseDto<>();
		Employees employees = employeesService.findById(id);
		if(employees == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(employees);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Page<Employees>>> findAll(@PathVariable int page, @PathVariable int count){
		ResponseDto<Page<Employees>> response = new ResponseDto<>();
		Page<Employees> employees = employeesService.findAll(page, count);
		response.setData(employees);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Employees>> delete(@PathVariable("id") Long id){
		ResponseDto<Employees> response = new ResponseDto<>();
		Employees employees = employeesService.findById(id);
		if(employees == null) {
			response.getErrors().add("Register not found id:" + id);
			return ResponseEntity.badRequest().body(response);
		}
		employeesService.delete(id);
		return ResponseEntity.ok(response);
	}

}
