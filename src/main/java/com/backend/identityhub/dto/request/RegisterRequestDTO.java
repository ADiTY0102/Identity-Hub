package com.backend.identityhub.dto.request;

import com.backend.identityhub.enums.Gender;
import com.backend.identityhub.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class RegisterRequestDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Long mobile;
	private String password;
	private Gender gender;
	private Role role; 
}
