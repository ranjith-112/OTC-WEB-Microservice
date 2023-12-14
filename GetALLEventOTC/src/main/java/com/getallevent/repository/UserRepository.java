package com.getallevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getallevent.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
