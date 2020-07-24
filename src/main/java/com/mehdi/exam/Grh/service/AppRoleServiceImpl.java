package com.mehdi.exam.Grh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mehdi.exam.Grh.dao.AppRoleRepository;
import com.mehdi.exam.Grh.dao.EmployeeRepository;
import com.mehdi.exam.Grh.entity.AppRole;
import com.mehdi.exam.Grh.entity.AppUser;

@Service
public class AppRoleServiceImpl implements AppRoleService {

	@Autowired
	private AppRoleRepository roleRepository;

	@Override
	public List<AppRole> getAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public void saveRole(AppRole role) {
		this.roleRepository.save(role);
	}

	@Override
	public AppRole getRoleById(long id) {
		Optional<AppRole> optional = roleRepository.findById(id);
		AppRole role = null;
		if (optional.isPresent()) {
			role = optional.get();
		} else {
			throw new RuntimeException(" role not found for id :: " + id);
		}
		return role;
	}

	@Override
	public void deleteRoleById(long id) {
		this.roleRepository.deleteById(id);
	}

	@Override
	public Page<AppRole> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.roleRepository.findAll(pageable);
	}
	
}
