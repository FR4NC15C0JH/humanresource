package com.humanresource.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.humanresource.domain.model.Departament;
import com.humanresource.domain.repository.jpa.DepartamentJpaRepository;

@Service
public class DepartamentService {
	
	@Autowired
	private DepartamentJpaRepository departamentJpaRepository;
	
	public Departament save(Departament departament) {
		return departamentJpaRepository.save(departament);
	}
	
	public void delete(Long id) {
		departamentJpaRepository.deleteById(id);
	}
	
	public Departament findOne(Long id) {
		return departamentJpaRepository.findById(id).orElse(null);
		/* return departamentJpaRepository.getOne(id); */
	}
	
	public List<Departament> findAll() {
		return departamentJpaRepository.findAll();
	}
	
	public Page<Departament> findAll(int page, int count) {
		Pageable pages = PageRequest.of(page, count);
		return departamentJpaRepository.findAll(pages);
	}
}
