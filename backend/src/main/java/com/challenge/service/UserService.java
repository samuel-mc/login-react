package com.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.model.User;
import com.challenge.repo.IUserRepo;

// Servicio donde se implementa la logica de negocio del modelo usuario
@Service
public class UserService {

	@Autowired
	private IUserRepo repository;
	
	/**
	 * Metodo para registrar un nuevo usuario, primero verifica que no este registrado su email.
	 * @param user Requiere el usuario (sus datos correspondientes).
	 * @return regresa null si el usuario esta registrado, en caso contrario, regresa en usuario creado.
	 */
	public User create(User user) {
		User existingUser = getByEmail(user.getEmail());
		if(existingUser != null)
			return null;		
		User userCreated = repository.save(user);
		return userCreated;
	}
	
	/**
	 * Metodo para obtener solamente un usuario.
	 * @param id Requiere el id del usuario a consultar.
	 * @return User Devuelve el usuario encontrado.
	 */
	public Optional<User> getOne(int id) {
		return repository.findById(id);
	}
	
	/**
	 * Metodo privado para encontrar un usuario a traves de su email.
	 * @param email Requiere el email del usuario a consultar.
	 * @return regresa el usuario encontrato.
	 */
	private User getByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	/**
	 * Metodo para obtener todos los usuarios registrados.
	 * @return List<User> Devuelve el listado de usuarios encontrados.
	 */
    public List<User> list() {
    	return repository.findAll();
    }
    
    /**
     * Metodo para actualizar la informacion de un usuario.
     * @param User Requiere la nueva información.
     * @return User regresa el usuario actualizado.
     */
    public User update(User user) {
    	User userUpdated = repository.save(user);
    	return userUpdated;
    }
    
    /**
     * Metodo para eliminar un usuario.
     * @param id Requiere el id del usuario a eliminar.
     */
    public void delete(int id) {
    	repository.deleteById(id);
    }
    
    
}
