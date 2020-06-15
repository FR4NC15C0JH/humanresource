package com.humanresource.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humanresource.domain.model.Job;
import com.humanresource.domain.model.dto.ResponseDto;
import com.humanresource.domain.service.JobService;
import com.humanresource.domain.validation.JobValidation;

@RestController
@RequestMapping("/api/job")
@CrossOrigin(origins="*")
public class JobController {

	@Autowired
	public JobService jobService;
	@Autowired
	public JobValidation jobValidation;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Job>> create(HttpServletRequest request, @RequestBody Job job, 
			BindingResult result){
		ResponseDto<Job> response = new ResponseDto<Job>();
		try {
			jobValidation.validateCreateJob(job, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Job jobPersisted = (Job) jobService.save(job);
			response.setData(jobPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<ResponseDto<Job>> findById(@PathVariable("id") Long id){
		ResponseDto<Job> response = new ResponseDto<Job>();
		Job job = jobService.findOne(id);
		if(job == null) {
			response.getErrors().add("Job not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(job);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Page<Job>>> findAll(@PathVariable int page, @PathVariable int count){
		ResponseDto<Page<Job>> response = new ResponseDto<Page<Job>>();
		Page<Job> jobs = jobService.findAll(page, count);
		response.setData(jobs);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/list")
	public ResponseEntity<ResponseDto<List<Job>>> list(){
		ResponseDto<List<Job>> response = new ResponseDto<List<Job>>();
		List<Job> listJobs = jobService.findAll();
		response.setData(listJobs);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Job>> delete(@PathVariable("id") Long id){
		ResponseDto<Job> response = new ResponseDto<Job>();
		Job job = jobService.findOne(id);
		if(job == null) {
			response.getErrors().add("Job not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.jobService.delete(id);
		return ResponseEntity.ok(response);		
	}
}

