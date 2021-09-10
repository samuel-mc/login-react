package com.challenge.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.auth.jwt.JwtProvider;
import com.challenge.model.User;
import com.challenge.repo.IUserRepo;

@RestController
public class AuthController {
	
	@Autowired
	private IUserRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	/**
	 * Ruta para logear a un usuario
	 * @param user Requiere un usario, especificamente su password y su contraseña
	 * @return	En caso de que no exista el correo o el password sea incorrecto regresa un estado de error, de lo contrario, regresa un token
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<String> create(@RequestBody User user) {
		String response;
		User userFound = repo.findByEmail(user.getEmail()); //Busca si el email existe en la base de datos.
		
		if(userFound == null) {
			response = "{\"messege\":\" Bad credentials \"}";
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND); //Si el usuario no se encontró, arrojamos un error.
		}
		
		boolean isValidPasword =  encoder.matches(user.getPassword(), userFound.getPassword()); //Valida si el password ingresado coincide con el que está alojado en la  bd
		
		if (isValidPasword == false) {
			response = "{\"messege\":\" Bad credentials \"}"; 
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND); //Si la contraseña no coincide, arrojamos un error.
		}
		
		String  token = jwtProvider.generateToken(user.getEmail());
		
		response = "{\"token\":\""+ token +"\"}";
		
	    return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
