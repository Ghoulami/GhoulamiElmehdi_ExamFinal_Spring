package com.mehdi.exam.Grh.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.mehdi.exam.Grh.utils.EncrytedPasswordUtils;


 
@Entity
@Table(name = "App_User", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })
public class AppUser{
 
	@Id
    @GeneratedValue
    @Column(name = "User_Id", nullable = false)	
    private Long userId;
 
    @Column(name = "User_Name", length = 36, nullable = false)
    @NotBlank
	@Size(min=2, max=128 , message = "Must be between 10 and 128 characters")
    private String userName;
 
    @Column(name = "FIRST_NAME", length = 128, nullable = false)
    @NotBlank
	@Size(min=2, max=128,message = "Must be between 10 and 128 characters")
    private String firstName;
    
    @Column(name = "lAST_NAME", length = 128, nullable = false)
    @NotBlank
	@Size(min=2, max=128 ,message = "Must be between 10 and 128 characters")
    private String lastName;
    
    @Column(name = "EMAIL", length = 255, nullable = false)
    @Email(message = "Email should be valid")
    @NotBlank
    private String email;
    
    @Column(name = "PHONE", length = 128, nullable = false)
    @NotBlank
	@Size(min=10, max=10 ,message = "Must be between 10 characters")
    private String phone;
    
    @Column(name = "SALERY", nullable = false)
    @Positive(message = "Must be positive number")
    private double salery;
    
    @Column(name = "Encryted_Password", length = 128, nullable = false)
    @NotBlank
    @Size(min=6 ,message = "Must be between 6 and 32 characters")
    private String encrytedPassword;
 
    @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "remuneartion_id",referencedColumnName = "id", nullable = true)
    private Remuneration remuneartion ;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id",referencedColumnName = "id", nullable = true)
    private Departement department;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supper_id",referencedColumnName = "user_id", nullable = true)
    private AppUser supperieur;

    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
		joinColumns = @JoinColumn(name = "User_Id", referencedColumnName = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "Role_Id", referencedColumnName = "role_id"))
	private List<AppRole> userRoles = new ArrayList<AppRole>();
    
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getEncrytedPassword() {
        return encrytedPassword;
    }
 
    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getSalery() {
		return salery;
	}

	public void setSalery(double salery) {
		this.salery = salery;
	}

	public Remuneration getRemuneartion() {
		return remuneartion;
	}

	public void setRemuneartion(Remuneration remuneartion) {
		this.remuneartion = remuneartion;
	}

	public Departement getDepartment() {
		return department;
	}

	public void setDepartment(Departement department) {
		this.department = department;
	}

	public AppUser getSupperieur() {
		return supperieur;
	}

	public void setSupperieur(AppUser supperieur) {
		this.supperieur = supperieur;
	}
	
	
	public List<AppRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<AppRole> userRoles) {
		this.userRoles = userRoles;
	}

}