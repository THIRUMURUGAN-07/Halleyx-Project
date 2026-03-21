package com.workflow.workflow_engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.workflow.workflow_engine.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable String id) {
		
		
		userRepository.deleteById(id);
		return "User delted with id " + id+" deleted succeddfully";
		
	}
}


