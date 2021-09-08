package com.challenge.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.model.User;
import com.challenge.repo.IUserRepo;

@RestController
public class AuthController {
	
	@Autowired
	private IUserRepo repo;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) {
		User us = repo.findByEmail(user.getEmail());
	    return new ResponseEntity<User>(us, HttpStatus.OK);
	}
}
