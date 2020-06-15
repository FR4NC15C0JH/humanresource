package com.humanresource.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.humanresource.domain.model.Job;
import com.humanresource.domain.repository.jpa.JobJpaRepository;

@Service
public class JobService {
	
	@Autowired
	private JobJpaRepository jobJpaRepository;
	
	public Job save(Job job) {
		return jobJpaRepository.save(job);
	}
	
	public void delete(Long id) {
		jobJpaRepository.deleteById(id);
	}
	
	public Job findOne(Long id) {
		return jobJpaRepository.findById(id).orElse(null);
		/* return jobJpaRepository.getOne(id); */
	}
	
	public List<Job> findAll() {
		return jobJpaRepository.findAll();
	}
	
	public Page<Job> findAll(int page, int count) {
		Pageable pages = PageRequest.of(page, count);
		return jobJpaRepository.findAll(pages);
	}
}
