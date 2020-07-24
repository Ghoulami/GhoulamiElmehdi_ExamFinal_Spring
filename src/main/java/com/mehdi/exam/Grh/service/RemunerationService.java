package com.mehdi.exam.Grh.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mehdi.exam.Grh.entity.Remuneration;


public interface RemunerationService {
	List<Remuneration> getAllRemunerations();
	void saveRemuneration(Remuneration employee);
	Remuneration getRemunerationById(long id);
	void deleteRemunerationById(long id);
	Page<Remuneration> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
