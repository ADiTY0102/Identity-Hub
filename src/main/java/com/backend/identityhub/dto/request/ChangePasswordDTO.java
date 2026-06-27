package com.backend.identityhub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor 
public class ChangePasswordDTO {
	private String oldPassword;
	private String newPassword;
}
