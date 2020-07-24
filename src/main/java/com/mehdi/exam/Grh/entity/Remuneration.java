package com.mehdi.exam.Grh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity	
@Table(name = "remuneration")
public class Remuneration implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private long id;
	
	@Column(name = "type")
    private String type;
    
    @OneToMany(mappedBy = "remuneartion", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private List<AppUser> employes = new ArrayList<AppUser>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<AppUser> getEmployes() {
		return employes;
	}

	public void setEmployes(List<AppUser> employes) {
		this.employes = employes;
	}

	

}
