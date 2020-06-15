package com.humanresource.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.humanresource.domain.model.Employees;
import com.humanresource.domain.repository.jpa.EmployeesJpaRepository;

@Service
public class EmployeesService {
	
	@Autowired
	private EmployeesJpaRepository employeesJpaRepository;
	
	public Employees save(Employees employees) {
		return employeesJpaRepository.save(employees);
	}
	
	public void delete(Long id) {
		employeesJpaRepository.deleteById(id);
	}
	
	public Employees findByEmail(String email) {
		return employeesJpaRepository.findByEmail(email);
	}
	
	public Employees findById(Long id) {
		return employeesJpaRepository.findById(id).orElse(null);
		/* return employeesJpaRepository.getOne(id); */
	}
	
	public Page<Employees> findAll(int page, int count) {
		Pageable pages = PageRequest.of(page, count);
		return employeesJpaRepository.findAll(pages);
	}
	
}
