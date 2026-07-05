package com.backend.identityhub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class LoginResponseDTO {
	private String accessToken;
	private String refreshToken;
	private UserResponseDTO user;
}
