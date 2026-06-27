package com.backend.identityhub.service;

import java.util.List;

import com.backend.identityhub.dto.request.ChangePasswordDTO;
import com.backend.identityhub.dto.request.RegisterRequestDTO;
import com.backend.identityhub.dto.response.UserResponseDTO;

public interface UserService {
	
	UserResponseDTO createUser(RegisterRequestDTO createUser);
	
	UserResponseDTO getUserById(Long id);
	
	List<UserResponseDTO> getAllUsers();
	
	UserResponseDTO updateUser(Long id,RegisterRequestDTO updateUser);
	
	void deleteUser(Long id);

    void changePassword(Long userId, ChangePasswordDTO request);

    UserResponseDTO activateUser(Long id);

    UserResponseDTO blockUser(Long id);

	RegisterRequestDTO changePassword(String oldPassword, String newPassword);
}
