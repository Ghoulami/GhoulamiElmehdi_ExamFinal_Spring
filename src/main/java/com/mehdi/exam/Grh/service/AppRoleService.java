package com.mehdi.exam.Grh.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.mehdi.exam.Grh.entity.AppRole;


public interface AppRoleService {
	List<AppRole> getAllRoles();
	void saveRole(AppRole role);
	AppRole getRoleById(long id);
	void deleteRoleById(long id);
	Page<AppRole> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
