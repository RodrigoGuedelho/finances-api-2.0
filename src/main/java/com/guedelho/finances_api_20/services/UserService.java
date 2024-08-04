package com.guedelho.finances_api_20.services;

import com.guedelho.finances_api_20.dtos.request.UserRequest;
import com.guedelho.finances_api_20.enums.GenericStatus;
import com.guedelho.finances_api_20.enums.UserRole;
import com.guedelho.finances_api_20.models.User;
import com.guedelho.finances_api_20.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class UserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public User save(User user) throws BadRequestException {
        User userExist = userRepository.findByLogin(user.getLogin());
        if (userExist != null)
            throw new BadRequestException("Usuário com esse email de login já existe.");

        user.setCreatedAt(OffsetDateTime.now());
        user.setUpdatedAt(OffsetDateTime.now());
        user.setRole(UserRole.USER);
        user.setStatus(GenericStatus.ACTIVE);
        //String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        //user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }




}
