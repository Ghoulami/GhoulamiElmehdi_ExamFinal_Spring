package com.mehdi.exam.Grh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.mehdi.exam.Grh.dao.RemunerationRepository;
import com.mehdi.exam.Grh.entity.Remuneration;

@Service
public class RemunerationServiceImpl implements RemunerationService {

	@Autowired
	private RemunerationRepository remunerationRepository;

	@Override
	public List<Remuneration> getAllRemunerations() {
		return remunerationRepository.findAll();
	}

	@Override
	public void saveRemuneration(Remuneration employee) {
		this.remunerationRepository.save(employee);
	}

	@Override
	public Remuneration getRemunerationById(long id) {
		Optional<Remuneration> optional = remunerationRepository.findById(id);
		Remuneration remuneration = null;
		if (optional.isPresent()) {
			remuneration = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return remuneration;
	}

	@Override
	public void deleteRemunerationById(long id) {
		this.remunerationRepository.deleteById(id);
	}

	@Override
	public Page<Remuneration> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.remunerationRepository.findAll(pageable);
	}
}
