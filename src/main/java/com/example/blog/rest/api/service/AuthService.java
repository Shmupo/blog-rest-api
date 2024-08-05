package com.example.blog.rest.api.service;

import com.example.blog.rest.api.payload.LoginDto;
import com.example.blog.rest.api.payload.RegisterDto;
import org.springframework.stereotype.Service;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
