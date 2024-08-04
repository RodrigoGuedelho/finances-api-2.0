package com.guedelho.finances_api_20.controllers;

import com.guedelho.finances_api_20.dtos.request.UserLogin;
import com.guedelho.finances_api_20.dtos.response.LoginResponseDTO;
import com.guedelho.finances_api_20.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
//RequestMapping("/auth/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLogin user){
        return ResponseEntity.ok(authenticationService.login(user));
    }
}
