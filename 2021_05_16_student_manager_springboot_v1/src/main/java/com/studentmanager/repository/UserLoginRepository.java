package com.studentmanager.repository;
import java.util.Optional;

import com.studentmanager.model.UserLogin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String> {
	Optional<UserLogin> findByUsername(String username);

	@Query(value = "SELECT CASE WHEN MAX(u.name) IS NULL THEN 'NO' ELSE 'YES' END"
			+ " FROM users u WHERE lower(u.name) like lower(:username)", nativeQuery = true)
	public Boolean existsByUsername(@Param("username") String username);

	@Query(value = "SELECT CASE WHEN count(u.email)>0 THEN 'YES' ELSE 'NO' END"
			+ " FROM users u WHERE lower(u.email) = lower(:email)", nativeQuery = true)
	public Boolean existsByEmail(@Param("email") String email);
}
