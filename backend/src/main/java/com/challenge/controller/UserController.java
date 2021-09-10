package com.challenge.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.auth.jwt.JwtProvider;
import com.challenge.model.User;
import com.challenge.service.UserService;


/* Controlador para el CRUD en la ruta de usuarios */
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User user) {
		User userCreated = userService.create(user);
		
		if(userCreated == null)
			return new ResponseEntity<User>(userCreated, HttpStatus.CONFLICT); //Si el usuario ya esta registrado, enviamos un estado de error.
		
        return new ResponseEntity<User>(userCreated, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<User>> readAll(){
		List<User> users = userService.list();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<User> readByToken(@RequestParam(name = "token") String token){
		String email = jwtProvider.getEmailFromToken(token);
		User user = userService.getByEmail(email);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<User>> readOneById(@PathVariable("id") Integer id){
		Optional<User> user = userService.getOne(id);
		return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@RequestBody User user) {
		User userUpdated = userService.update(user);
		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		userService.delete(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
