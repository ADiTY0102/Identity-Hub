package com.backend.identityhub.repository;


import java.util.Optional;
import java.util.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.identityhub.entity.UserEntity;
import com.backend.identityhub.enums.Role;
import com.backend.identityhub.enums.UserStatus;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	Optional<UserEntity> findByEmailAndIsDeletedFalse(String email);

	Optional<UserEntity> findByMobileAndIsDeletedFalse(Long mobile);
	
	Optional<UserEntity> findByIdAndIsDeletedFalse(Long id);
	
	List<UserEntity> findAllByIsDeletedFalse();
	
	Boolean existsByEmail(String email);
	
	Boolean existsByMobile(Long mobile);
	
	List<UserEntity> findByRole(Role role);

	List<UserEntity> findByStatus(UserStatus status);

	Page<UserEntity> findByRole(Role role, Pageable pageable);

	Page<UserEntity> findByStatus(UserStatus status, Pageable pageable);

	List<UserEntity> findByIsDeletedFalse();

	boolean existsByEmailAndIdNot(String email, Long id);
}
