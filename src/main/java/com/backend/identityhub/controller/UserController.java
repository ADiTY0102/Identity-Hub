package com.backend.identityhub.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.identityhub.dto.request.ChangePasswordDTO;
import com.backend.identityhub.dto.request.RegisterRequestDTO;
import com.backend.identityhub.dto.response.UserResponseDTO;
import java.util.List;
import com.backend.identityhub.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	private final UserService userService;
	/*
	 * Create user 
	 * POST /api/v1/users
	 * */
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserResponseDTO> createUser(
			@Valid
			@RequestBody
			RegisterRequestDTO request){
		UserResponseDTO response = userService.createUser(request);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(response);
		
	}
	
	/*
	 * Get user by {id} 
	 * GET /api/v1/users/{id}
	 * */
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<UserResponseDTO>getUserById(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	/*
	 * Get all user
	 * GET /api/v1/users
	 * */
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity <List<UserResponseDTO>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	
	/*
	 * Update user by {id} 
	 * PUT /api/v1/users/{id}
	 * */
	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	public ResponseEntity<UserResponseDTO> updateUser(
	        @PathVariable Long id,
	        @Valid @RequestBody RegisterRequestDTO request) {

	    return ResponseEntity.ok(userService.updateUser(id, request));
	}
	
	   /*
     * Soft Delete User
     * DELETE /api/v1/users/{id}
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
    /*
     * Activate the user
     * PATCH /api/v1/users/activate/{id}
     */
    @PatchMapping("/activate/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> activateUser(@PathVariable Long id){
    	return ResponseEntity.ok(userService.activateUser(id));
    }
    /*
     * Block the user
     * PATCH /api/v1/users/block/{id}
     */
    @PatchMapping("/block/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> blockUser(@PathVariable Long id){
    	return ResponseEntity.ok(userService.blockUser(id));
    }
    /*
     * Change Password
     * PATCH /api/v1/users/change-password/{id}
     */
    @PatchMapping("/change-password/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> changePassword(
            @PathVariable Long id,
            @Valid @RequestBody ChangePasswordDTO request) {

        userService.changePassword(id, request);

        return ResponseEntity.ok("Password changed successfully.");
    }
}
