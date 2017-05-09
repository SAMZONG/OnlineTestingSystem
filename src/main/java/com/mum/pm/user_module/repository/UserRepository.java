package com.mum.pm.user_module.repository;

import com.mum.pm.user_module.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	@Query("SELECT b FROM User b")
	List<User> getAllUsers();
}
