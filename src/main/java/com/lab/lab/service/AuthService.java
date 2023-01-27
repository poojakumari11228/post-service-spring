package com.lab.lab.service;

import com.lab.lab.dto.request.LoginRequest;
import com.lab.lab.dto.request.RefreshTokenRequest;
import com.lab.lab.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
