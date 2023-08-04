package com.te.jspiders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.jspiders.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

	Optional<AppUser> findByUsername(String username);

}
