package com.mehdi.exam.Grh.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.mehdi.exam.Grh.dao.AppUserDAO;
import com.mehdi.exam.Grh.entity.AppRole;
import com.mehdi.exam.Grh.entity.AppUser;
import com.mehdi.exam.Grh.entity.UserRole;
import com.mehdi.exam.Grh.service.AppRoleService;
import com.mehdi.exam.Grh.service.DepartmentService;
import com.mehdi.exam.Grh.service.EmployeeService;
import com.mehdi.exam.Grh.service.RemunerationService;
import com.mehdi.exam.Grh.utils.EncrytedPasswordUtils;
import com.mehdi.exam.Grh.utils.WebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
public class MainController {
	
	 @Autowired
	    private AppUserDAO appUserDAO;
	 
	 @Autowired
		private EmployeeService employeeService;
	 
	 @Autowired
		private RemunerationService  remunerationService;
	 
	 @Autowired
		private AppRoleService  roleService;
	 
	 @Autowired
		private DepartmentService  departmentService;
	 
	 
 
    @RequestMapping(value =  "/" , method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }
 
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "loginPage";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
 
    @RequestMapping(value = "/userInfo" , method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
   	
        // After user login successfully.
    	
        String userName = principal.getName();
        
        System.out.println("User Name: " + userName);
        
        AppUser appUser = this.appUserDAO.findUserAccount(userName);
        
        System.out.println("first Name: " + appUser.getSalery());
        
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("emplyee", appUser);
 
        return "userInfoPage";
    }
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
    
    
 // ---------------------------------------------display list of employees------------------------------------------------
 	@GetMapping("/admin")
 	public String viewHomePage(Model model) {
 		return findPaginated(1, "firstName", "asc", model);		
 	}
 	
 	@GetMapping("/showNewEmployeeForm")
 	public String showNewEmployeeForm(AppUser employee,Model model) {
 		// create model attribute to bind form data
 		employee = new AppUser();
 		model.addAttribute("employee", employee);
 		model.addAttribute("remunerations", remunerationService.getAllRemunerations());
 		model.addAttribute("supperieurs", employeeService.getAllEmployees());
 		model.addAttribute("roles",roleService.getAllRoles());
 		model.addAttribute("departments",departmentService.getAllDepartemets());
 		
 		System.out.println(employeeService.getAllEmployees());
 		
 		return "new_employee";
 	}
 	
 	@PostMapping("/saveEmployee")
 	public String saveEmployee(@Valid @ModelAttribute("employee") AppUser employee ,BindingResult bindingResult, @RequestParam(name = "dep_id" , required = false) Long dep_id
 			, @RequestParam(name = "remu_id" , required = false) Long remu_id , @RequestParam(name = "sup_id" , required = false) Long sup_id ,
 			@RequestParam(name = "Roles" , required = false) List<String> Roles ) {
 		// save employee to database
 		
 		if (bindingResult.hasErrors()) {
			return "new_employee";
		}
 		
 		List<AppRole> userRoles = new ArrayList<AppRole>();
 		
 		if(dep_id != null) {
 			System.out.println("-----"+dep_id+"-----");
 	 		employee.setDepartment(departmentService.getDepartementById(dep_id));
 		}
 		if(sup_id != null) {
 			System.out.println("-----"+sup_id+"-----");

 	 		employee.setSupperieur(employeeService.getEmployeeById(sup_id));

 		}
 		if(remu_id != null) {
 			System.out.println("-----"+remu_id+"-----");

 	 		employee.setRemuneartion(remunerationService.getRemunerationById(remu_id));
 		}
 		
 		for(int i = 0 ; i < Roles.size(); i++) {
 			userRoles.add(roleService.getRoleById(Long.parseLong(Roles.get(i))));
 		}
 		
		employee.setUserRoles(userRoles);
		employee.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(employee.getEncrytedPassword()));
 		employeeService.saveEmployee(employee);
 		return "redirect:/admin";
 	}
 	
 	@GetMapping("/showFormForUpdate/{id}")
 	public String showFormForUpdate(AppUser employee,@PathVariable ( value = "id") long id, Model model) {
 		
 		// get employee from the service
 		employee = employeeService.getEmployeeById(id);
 		model.addAttribute("remunerations", remunerationService.getAllRemunerations());
 		model.addAttribute("supperieurs", employeeService.getAllEmployees());
 		model.addAttribute("roles",roleService.getAllRoles());
 		model.addAttribute("departments",departmentService.getAllDepartemets());
 		// set employee as a model attribute to pre-populate the form
 		model.addAttribute("employee", employee);
 		
 		System.out.println(employeeService.getAllEmployees());
 		return "update_employee";
 	}
 	
 	@GetMapping("/deleteEmployee/{id}")
 	public String deleteEmployee(@PathVariable (value = "id") long id) {
 		
 		// call delete employee method 
 		this.employeeService.deleteEmployeeById(id);
 		return "redirect:/admin";
 	}
 	
 	
 	@GetMapping("/page/{pageNo}")
 	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
 			@RequestParam("sortField") String sortField,
 			@RequestParam("sortDir") String sortDir,
 			Model model) {
 		int pageSize = 5;
 		
 		Page<AppUser> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
 		List<AppUser> listEmployees = page.getContent();
 		
 		model.addAttribute("currentPage", pageNo);
 		model.addAttribute("totalPages", page.getTotalPages());
 		model.addAttribute("totalItems", page.getTotalElements());
 		
 		model.addAttribute("sortField", sortField);
 		model.addAttribute("sortDir", sortDir);
 		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
 		
 		model.addAttribute("listEmployees", listEmployees);
 		return "index";
 	}
 
}