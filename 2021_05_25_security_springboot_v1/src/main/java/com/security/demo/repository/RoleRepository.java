package com.security.demo.repository;

import java.util.Optional;

import com.security.demo.models.ERole;
import com.security.demo.models.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
