package com.backend.identityhub.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.backend.identityhub.enums.Gender;
import com.backend.identityhub.enums.Role;
import com.backend.identityhub.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", nullable = false, length = 512)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 512)
	private String lastName;

	@Column(name = "email", nullable = false, unique = true, length = 255)
	private String email;

	@Column(name = "mobile_no", nullable = false, unique = true)
	private Long mobile;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "failed_login_attempts")
	private int failedLoginAttempts;

	@Column(name = "account_locked")
	private boolean accountLocked;

	private Boolean isDeleted;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	private LocalDate lastLogin;

}
