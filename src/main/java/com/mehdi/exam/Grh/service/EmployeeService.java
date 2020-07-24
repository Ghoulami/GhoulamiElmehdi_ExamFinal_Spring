package com.mehdi.exam.Grh.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mehdi.exam.Grh.entity.AppUser;


public interface EmployeeService {
	List<AppUser> getAllEmployees();
	void saveEmployee(AppUser employee);
	AppUser getEmployeeById(long id);
	void deleteEmployeeById(long id);
	Page<AppUser> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
