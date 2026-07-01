package com.backend.identityhub.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
	
	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	private SecretKey getSigningKey() {

	    byte[] keyBytes = Decoders.BASE64.decode(secretKey);

	    return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(String email) {

	    return generateToken(new HashMap<>(), email);
	}

	public String generateToken(Map<String,Object> extraClaims,String email) {
		return Jwts.builder()
				.claims(extraClaims)
				.subject(email)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+ jwtExpiration))
				.signWith(getSigningKey())
				.compact();
	}
	
	public String extractUsername(String token) {

	    return extractClaim(token, Claims::getSubject);
	}

	public<T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public boolean isTokenExpired(String token) {

	    return extractClaim(token, Claims::getExpiration)
	            .before(new Date());
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {

	    final String username = extractUsername(token);

	    return username.equals(userDetails.getUsername())
	            && !isTokenExpired(token);
	}

}
