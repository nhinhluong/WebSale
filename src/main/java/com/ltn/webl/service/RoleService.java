package com.ltn.webl.service;

import java.util.List;
import java.util.Optional;

import com.ltn.webl.entity.Role;

public interface RoleService {

	List<Role> getAllRole();  

	  void saveRole(Role Role);  

	  void deleteRole(Long id);  
	  
	  Optional<Role> findRoleById(Long id); 

	  public Role findRById(Long id); 
}
