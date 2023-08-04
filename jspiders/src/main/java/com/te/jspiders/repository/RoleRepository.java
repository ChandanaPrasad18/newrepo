package com.te.jspiders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.jspiders.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(String string);

}
