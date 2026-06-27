package com.backend.identityhub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LoginRequestDTO {
	private String emailId;
	private String password;
}
