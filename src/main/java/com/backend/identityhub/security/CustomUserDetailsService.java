package com.backend.identityhub.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.identityhub.entity.UserEntity;
import com.backend.identityhub.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException{
		UserEntity user = userRepository
				.findByEmailAndIsDeletedFalse(username)
				.orElseThrow(()->
				new UsernameNotFoundException("User Not Find with Email: "+username));
		return new CustomUserDetails(user);
		
	}
}
