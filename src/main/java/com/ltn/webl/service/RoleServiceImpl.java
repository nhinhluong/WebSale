package com.ltn.webl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltn.webl.entity.Role;
import com.ltn.webl.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository rolerepo;
	@Override
	public Role findRole(String role) {
		// TODO Auto-generated method stub
		return rolerepo.findByRole(role);
	}
	
	@Override
	public void saveRole(Role role) {
		// TODO Auto-generated method stub
		rolerepo.save(role);
	}


	@Override
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		return (List<Role>) rolerepo.findAll();
	}

}
