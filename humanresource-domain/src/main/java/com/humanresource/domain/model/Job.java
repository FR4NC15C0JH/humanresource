package com.humanresource.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Job implements Serializable{
	
	private static final long serialVersionUID = 5457232597823235403L;

	public static final String JOB_ID = "job_id";
	
	@Id
	@SequenceGenerator(name = "job", sequenceName = "seq_job", allocationSize = 1)
	@GeneratedValue(generator = "job", strategy = GenerationType.SEQUENCE)
	@Column(name = JOB_ID)
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
