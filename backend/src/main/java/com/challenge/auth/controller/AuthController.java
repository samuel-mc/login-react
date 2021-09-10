package com.challenge.auth.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

import com.challenge.model.User;
import com.challenge.repo.IUserRepo;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.Jwts;

@RestController
public class AuthController {
	
	@Autowired
	private IUserRepo repo;
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<String> create(@RequestBody User user) {
		String response;
		User userFound = repo.findByEmail(user.getEmail());
		
		if(userFound == null)
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		
		String  token = getJWTToken(user.getEmail());		
		
		response = "{\"token\":\""+ token +"\"}";
		
	    return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	private String getJWTToken(String username) {
		String secretKey = "signUpSectetKey";
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();
		return token;
	}
	
	
	
}
