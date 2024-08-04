package com.guedelho.finances_api_20.controllers;

import com.guedelho.finances_api_20.dtos.request.UserRequest;
import com.guedelho.finances_api_20.dtos.response.UserResponse;
import com.guedelho.finances_api_20.models.User;
import com.guedelho.finances_api_20.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest user) throws BadRequestException {
        User userResponse = userService.save(toEntity(user));
        return ResponseEntity.ok(toResponse(userResponse));
    }

    private User toEntity(UserRequest user){
        return modelMapper.map(user, User.class);
    }

    private UserResponse toResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }
}
