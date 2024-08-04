package com.guedelho.finances_api_20.services;

import com.guedelho.finances_api_20.dtos.request.UserLogin;
import com.guedelho.finances_api_20.dtos.response.LoginResponseDTO;
import com.guedelho.finances_api_20.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.guedelho.finances_api_20.models.User;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    public LoginResponseDTO login(UserLogin user){
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.login(), user.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }
}
