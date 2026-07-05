package com.backend.identityhub.dto.response;

import com.backend.identityhub.enums.Gender;
import com.backend.identityhub.enums.Role;
import com.backend.identityhub.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private Long mobile;

	private Role role;

	private Gender gender;

	private UserStatus status;
}
