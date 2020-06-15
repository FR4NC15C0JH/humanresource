package com.humanresource.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humanresource.domain.model.Employees;

@Repository
public interface EmployeesJpaRepository extends JpaRepository<Employees, Long>{
	
	Employees findByEmail(String email);
}
