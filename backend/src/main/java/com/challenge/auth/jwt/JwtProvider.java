package com.challenge.auth.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtProvider {
	String secretKey = "signUpSectetKey";
	
	Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	public String generateToken(String username) {
		return Jwts
				.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
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
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Token Mal Informado");
		} catch (UnsupportedJwtException e) {
			logger.error("Token no soportado");
		} catch (ExpiredJwtException e) {
			logger.error("Token Expirado");
		} catch (IllegalArgumentException e) {
			logger.error("Token Vacio");
		} catch (SignatureException e) {
			logger.error("Falla en la firma");
		}
		return false;
	}
	
	public boolean validateHeaderToken(HttpServletRequest request) {
		String jwtToken = request.getHeader("Authorization").replace("Bearer", "");
		return validateToken(jwtToken);

	}
}
