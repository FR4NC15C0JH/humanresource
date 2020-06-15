package com.humanresource.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.humanresource.domain.enumerator.Profiles;
import com.humanresource.domain.model.Employees;
import com.humanresource.domain.service.EmployeesService;
import com.humanresource.rest.config.HRWebConfigurationParameters;

@SpringBootApplication
@ComponentScan(basePackages = {HRWebConfigurationParameters.PACKAGES_DOMAIN_ALL})
@EnableJpaRepositories(basePackages= {HRWebConfigurationParameters.PACKAGES_DOMAIN_REPOSITORY_JPA})
@EntityScan(basePackages= {HRWebConfigurationParameters.PACKAGES_DOMAIN_MODEL})
public class HRApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(HRApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(EmployeesService employeesService, PasswordEncoder passwordEncoder) {
		return args -> {
			initUser(employeesService, passwordEncoder);
		};
	}
	
	private void initUser(EmployeesService employeesService, PasswordEncoder passwordEncoder) {
		Employees admin = new Employees();
		admin.setEmail("admin@employees.com");
		admin.setPassword(passwordEncoder.encode("123456"));
		admin.setProfiles(Profiles.ROLE_ADMIN.getDescricao());
		
		Employees find = employeesService.findByEmail("admin@employees.com");
		if(find == null) {
			employeesService.save(admin);
		}
	}
}
