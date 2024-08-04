package com.guedelho.finances_api_20;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.guedelho.finances_api_20.enums.UserRole;
import com.guedelho.finances_api_20.models.User;
import com.guedelho.finances_api_20.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	@Autowired
	public static TokenService tokenService;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
