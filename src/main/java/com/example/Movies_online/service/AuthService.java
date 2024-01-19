package com.example.Movies_online.service;

import com.example.Movies_online.dto.AuthLoginRequest;
import com.example.Movies_online.dto.AuthLoginResponse;
import com.example.Movies_online.dto.UserRegisterRequest;
import com.example.Movies_online.entities.User;

public interface AuthService {
    void register(UserRegisterRequest userRegisterRequest);

    AuthLoginResponse login(AuthLoginRequest authLoginRequest);

    User getUsernameFromToken(String token);
}