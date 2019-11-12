package com.ltn.webl.service;

import java.util.List;

import com.ltn.webl.entity.Role;

public interface RoleService {

	public Role findRole(String role);

	public void saveRole(Role role);
	
	List<Role> getAllRole();  
}
