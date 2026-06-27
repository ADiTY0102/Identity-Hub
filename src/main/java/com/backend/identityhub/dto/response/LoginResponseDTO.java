package com.backend.identityhub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class LoginResponseDTO {
	private String token;
	private String refreshToken;
	UserResponseDTO user;
}
