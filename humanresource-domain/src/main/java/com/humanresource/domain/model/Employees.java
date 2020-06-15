package com.humanresource.domain.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Employees implements Serializable{
	
	private static final long serialVersionUID = 2770429072764789307L;

	public static final String EMPLOYEES_ID = "employees_id";
	
	@Id
	@SequenceGenerator(name = "employees", sequenceName = "seq_employees", allocationSize = 1)
	@GeneratedValue(generator = "employees", strategy = GenerationType.SEQUENCE)
	@Column(name = EMPLOYEES_ID, insertable = false, updatable = false)
	private Long id;
	@Column
	private String name;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column
	private String image;
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name=Departament.DEPARTAMENT_ID)
	private Departament departament;
	@ManyToOne(cascade=CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name=Job.JOB_ID)
	private Job job;
	@Column
	private String profiles;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Departament getDepartament() {
		return departament;
	}

	public void setDepartament(Departament departament) {
		this.departament = departament;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getProfiles() {
		return profiles;
	}

	public void setProfiles(String profiles) {
		this.profiles = profiles;
	}

}
