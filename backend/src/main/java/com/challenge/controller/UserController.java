package com.challenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.model.User;
import com.challenge.repo.IUserRepo;


/* Controlador para el CRUD en la ruta de usuarios */
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private IUserRepo repo; 
	
	@PostMapping //Insertar un nuevo usuario.
	public void create(@RequestBody User user) {
		repo.save(user);
	}
	
	@GetMapping //Obtiene todos los usuarios.
	public List<User> readAll(){
		return repo.findAll();
	}

	@GetMapping(value = "/{id}") //Obtener un usuario por id
	public Optional<User> readOneById(@PathVariable("id") Integer id){
		return repo.findById(id);
		
	}
	
	/* ToDo: Agregar obtener usuario por email */
	
	
	@PutMapping //Modificar cierto usuario.
	public void update(@RequestBody User user) {
		repo.save(user);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Integer id) {
		repo.deleteById(id);
	}
}
