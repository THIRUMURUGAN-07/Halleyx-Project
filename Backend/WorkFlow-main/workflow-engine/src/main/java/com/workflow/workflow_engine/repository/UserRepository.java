package com.workflow.workflow_engine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workflow.workflow_engine.entity.User;

public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email);

}
