package com.mehdi.exam.Grh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mehdi.exam.Grh.dao.DepartmentRepository;
import com.mehdi.exam.Grh.entity.Departement;

@Service
public class DepartementServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departementRepository;

	@Override
	public List<Departement> getAllDepartemets() {
		return departementRepository.findAll();
	}

	@Override
	public void saveDepartemet(Departement employee) {
		this.departementRepository.save(employee);
	}

	@Override
	public Departement getDepartementById(long id) {
		Optional<Departement> optional = departementRepository.findById(id);
		Departement employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteDepartementById(long id) {
		this.departementRepository.deleteById(id);
	}

	@Override
	public Page<Departement> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.departementRepository.findAll(pageable);
	}
	
	
}
