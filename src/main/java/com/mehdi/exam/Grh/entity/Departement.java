package com.mehdi.exam.Grh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.mehdi.exam.Grh.service.AppRoleService;
import com.mehdi.exam.Grh.service.DepartmentService;
import com.mehdi.exam.Grh.service.EmployeeService;
import com.mehdi.exam.Grh.service.RemunerationService;

@Entity
@Table(name = "departement")
public class Departement implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	
	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private List<AppUser> employes = new ArrayList<AppUser>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppUser> getEmployes() {
		return employes;
	}

	public void setEmployes(List<AppUser> employes) {
		this.employes = employes;
	}


}
