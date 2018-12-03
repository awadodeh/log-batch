package com.ef.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ef.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
