package com.ltn.webl.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ltn.webl.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

 Role findByRole(String role);
 Role findRById(Long id);
 
}
