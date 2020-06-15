package com.humanresource.domain.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humanresource.domain.model.Departament;

@Repository
public interface DepartamentJpaRepository extends JpaRepository<Departament, Long>{

}
