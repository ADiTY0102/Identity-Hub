package com.backend.identityhub.dto.request;

import com.backend.identityhub.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class UpdateUserDTO {
	
	private String firstName;

	private String lastName;

	private String mobile;

	private Gender gender;
}
