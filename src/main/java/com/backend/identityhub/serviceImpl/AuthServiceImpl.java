package com.backend.identityhub.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.identityhub.dto.request.LoginRequestDTO;
import com.backend.identityhub.dto.request.RegisterRequestDTO;
import com.backend.identityhub.dto.response.LoginResponseDTO;
import com.backend.identityhub.dto.response.UserResponseDTO;
import com.backend.identityhub.entity.UserEntity;
import com.backend.identityhub.enums.Role;
import com.backend.identityhub.enums.UserStatus;
import com.backend.identityhub.exception.ResourceNotFoundException;
import com.backend.identityhub.exception.UserAlreadyExistsException;
import com.backend.identityhub.mapper.UserMapper;
import com.backend.identityhub.repository.UserRepository;
import com.backend.identityhub.security.JwtService;
import com.backend.identityhub.service.AuthService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public LoginResponseDTO login(LoginRequestDTO request) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()));

		UserEntity user = userRepository
				.findByEmailAndIsDeletedFalse(request.getEmail())
				.orElseThrow(() -> new UsernameNotFoundException(
						"User not found with email : "
								+ request.getEmail()));

		String accessToken = jwtService.generateToken(user.getEmail());

		return LoginResponseDTO.builder()
				.accessToken(accessToken)
				.refreshToken(null)
				.user(userMapper.toResponse(user))
				.build();
	}

	@Override
	public UserResponseDTO register(RegisterRequestDTO request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistsException(
					"User already exists with email : " + request.getEmail());
		}

		UserEntity user = new UserEntity();
		user.setEmail(request.getEmail());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		user.setGender(request.getGender());
		user.setStatus(UserStatus.ACTIVE);
		user.setAccountLocked(false);
		user.setIsDeleted(false);
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());

		UserEntity savedUser = userRepository.save(user);

		return userMapper.toResponse(savedUser);
	}
}
