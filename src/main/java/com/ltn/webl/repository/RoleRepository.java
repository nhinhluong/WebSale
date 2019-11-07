package com.ltn.webl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltn.webl.entity.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

 Role findByRole(String role);
}
