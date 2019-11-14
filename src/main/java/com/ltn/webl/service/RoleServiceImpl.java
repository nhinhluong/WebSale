package com.ltn.webl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltn.webl.entity.Role;
import com.ltn.webl.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> getAllRole() {
		return (List<Role>) roleRepository.findAll(); 
	}

	@Override
	public void deleteRole(Long id) {
		// TODO Auto-generated method stub
		roleRepository.deleteById(id);
	}

	@Override
	public void saveRole(Role role) {
		// TODO Auto-generated method stub
		roleRepository.save(role);
	}

	@Override
	public Role findRById(Long id) {
		// TODO Auto-generated method stub
		return roleRepository.findRById(id);
	}

	@Override
	public Optional<Role> findRoleById(Long id) {
		// TODO Auto-generated method stub
		return roleRepository.findById(id);
	}

}
