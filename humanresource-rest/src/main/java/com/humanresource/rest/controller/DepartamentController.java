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

import com.humanresource.domain.model.Departament;
import com.humanresource.domain.model.dto.ResponseDto;
import com.humanresource.domain.service.DepartamentService;
import com.humanresource.domain.validation.DepartamentValidation;

@RestController
@RequestMapping("/api/departament")
@CrossOrigin(origins="*")
public class DepartamentController {

	@Autowired
	public DepartamentService departamentService;
	@Autowired
	public DepartamentValidation departamentValidation;
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Departament>> create(HttpServletRequest request, @RequestBody Departament departament, 
			BindingResult result){
		ResponseDto<Departament> response = new ResponseDto<Departament>();
		try {
			departamentValidation.validateCreateDepartament(departament, result);
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Departament departamentPersisted = (Departament) departamentService.save(departament);
			response.setData(departamentPersisted);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<ResponseDto<Departament>> findById(@PathVariable("id") Long id){
		ResponseDto<Departament> response = new ResponseDto<Departament>();
		Departament departament = departamentService.findOne(id);
		if(departament == null) {
			response.getErrors().add("Departament not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(departament);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "{page}/{count}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Page<Departament>>> findAll(@PathVariable int page, @PathVariable int count){
		ResponseDto<Page<Departament>> response = new ResponseDto<Page<Departament>>();
		Page<Departament> departaments = departamentService.findAll(page, count);
		response.setData(departaments);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping()
	@RequestMapping("/list")
	//@PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
	public ResponseEntity<ResponseDto<List<Departament>>> list(){
		ResponseDto<List<Departament>> response = new ResponseDto<List<Departament>>();
		List<Departament> departaments = departamentService.findAll();
		response.setData(departaments);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<ResponseDto<Departament>> delete(@PathVariable("id") Long id){
		ResponseDto<Departament> response = new ResponseDto<Departament>();
		Departament departament = departamentService.findOne(id);
		if(departament == null) {
			response.getErrors().add("Departament not found id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.departamentService.delete(id);
		return ResponseEntity.ok(response);		
	}
	
}

