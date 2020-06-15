package com.humanresource.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humanresource.domain.model.Job;

@Repository
public interface JobJpaRepository extends JpaRepository<Job, Long>{

}
