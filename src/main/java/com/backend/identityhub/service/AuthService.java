package com.backend.identityhub.service;

import com.backend.identityhub.dto.request.LoginRequestDTO;
import com.backend.identityhub.dto.request.RegisterRequestDTO;
import com.backend.identityhub.dto.response.LoginResponseDTO;
import com.backend.identityhub.dto.response.UserResponseDTO;

public interface AuthService {

    LoginResponseDTO login(LoginRequestDTO request);

    UserResponseDTO register(RegisterRequestDTO request);

}