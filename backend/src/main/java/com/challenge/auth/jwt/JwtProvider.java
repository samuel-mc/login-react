package com.challenge.auth.jwt;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtProvider {
	String secretKey = "signUpSectetKey";
	
	public String generateToken(String username) {
		return Jwts
				.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
	
	public String getEmailFromToken(String token) {
		return Jwts
				.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}
