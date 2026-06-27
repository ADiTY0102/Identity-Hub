package com.backend.identityhub.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.identityhub.dto.request.ChangePasswordDTO;
import com.backend.identityhub.dto.request.RegisterRequestDTO;
import com.backend.identityhub.dto.response.UserResponseDTO;
import com.backend.identityhub.entity.UserEntity;
import com.backend.identityhub.enums.Role;
import com.backend.identityhub.enums.UserStatus;
import com.backend.identityhub.exception.ResourceNotFoundException;
import com.backend.identityhub.exception.UserAlreadyExistsException;
import com.backend.identityhub.repository.UserRepository;
import com.backend.identityhub.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepo;
//	private final PasswordEncoder passEncoder;

	private UserResponseDTO mapToResponse(UserEntity user) {

	    return UserResponseDTO.builder()
	            .id(user.getId())
	            .firstName(user.getFirstName())
	            .lastName(user.getLastName())
	            .emailId(user.getEmail())
	            .mobile(user.getMobile())
	            .role(user.getRole())
	            .gender(user.getGender())
	            .status(user.getStatus())
	            .build();
	}
	
	/* 1)
	 * User Create Request and Response Mapping 
	 * */
	
	@Override
	public UserResponseDTO createUser(RegisterRequestDTO request) {
		
		//email existence check
		if(userRepo.findByEmailAndIsDeletedFalse(request.getEmail()).isPresent()) {
			throw new UserAlreadyExistsException(
				"User Already Exists with Provided Email: "+request.getEmail()
			);
		}
		//mobile number check
		if(userRepo.findByMobileAndIsDeletedFalse(request.getMobile()).isPresent()) {
			throw new UserAlreadyExistsException(
					"User Already Exists with Provided Mobile No.: "+request.getMobile()
					);
		}
		
		UserEntity user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .password(request.getPassword())
                .gender(request.getGender())
//                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .status(UserStatus.ACTIVE)
                .isDeleted(false)
                .accountLocked(false)
                .failedLoginAttempts(0)
                .build();
		
		UserEntity savedUser = userRepo.save(user);
				
		return mapToResponse(savedUser);
	}
	

	/* 2)
	 * Get user Data By Id 
	 * */
	@Override
	public UserResponseDTO getUserById(Long id) {
		
		UserEntity user = userRepo.findById(id)
		        .orElseThrow(() ->
		            new ResourceNotFoundException(
		                "User Not Found For Id: " + id));
		return mapToResponse(user);
	}

	
	/* 2)
	 * Get all user Data 
	 * */

	@Override
	public List<UserResponseDTO> getAllUsers() {

	    return userRepo.findAll()
	            .stream()
	            .map(this::mapToResponse)
	            .toList();
	}
	
	/* 3)
	 * Update user Data By Id 
	 * */
	
	@Override
	public UserResponseDTO updateUser(Long id, RegisterRequestDTO updateUser)  throws ResourceNotFoundException {
		
		UserEntity user = userRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException(
		                "User Not Found For Id: " + id));
		
		
		if (!user.getEmail().equals(updateUser.getEmail())
		        && userRepo.findByEmailAndIsDeletedFalse(updateUser.getEmail()).isPresent()) {
		    throw new UserAlreadyExistsException("Email already exists");
		}
		
		user.setFirstName(updateUser.getFirstName());
		user.setLastName(updateUser.getLastName());
		user.setEmail(updateUser.getEmail());
		user.setMobile(updateUser.getMobile());
		user.setGender(updateUser.getGender());
		
		UserEntity updatedUser = userRepo.save(user);
		return mapToResponse(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {

	    UserEntity user = userRepo.findByIdAndIsDeletedFalse(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "User not found with ID: " + id));

	    // SOFT DELETE 		
	    if (Boolean.TRUE.equals(user.getIsDeleted())) {
	        throw new IllegalStateException("User is already deleted.");
	    }

	    user.setIsDeleted(true);
	    user.setStatus(UserStatus.DELETED);
	    user.setAccountLocked(true);

	    userRepo.save(user);
	}


	@Override
	public UserResponseDTO activateUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDTO blockUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(Long userId, ChangePasswordDTO request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RegisterRequestDTO changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
