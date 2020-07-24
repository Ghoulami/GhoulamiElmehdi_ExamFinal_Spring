package com.mehdi.exam.Grh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mehdi.exam.Grh.entity.AppUser;

@Repository
public interface EmployeeRepository extends JpaRepository<AppUser, Long>{

}
