package com.humanresource.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Departament implements Serializable{
	
	private static final long serialVersionUID = 6839803027000237872L;

	public static final String DEPARTAMENT_ID = "departament_id";
	
	@Id
	@SequenceGenerator(name = "departament", sequenceName = "seq_departament", allocationSize = 1)
	@GeneratedValue(generator = "departament", strategy = GenerationType.SEQUENCE)
	@Column(name = DEPARTAMENT_ID)
	private Long id;
	@Column
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
