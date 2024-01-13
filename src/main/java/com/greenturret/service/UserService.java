package com.greenturret.service;

import com.greenturret.dto.LoginDTO;
import com.greenturret.response.ErrorConsts;
import com.greenturret.dto.UserDTO;
import com.greenturret.exception.UsernameAlreadyExistsException;
import com.greenturret.model.User;
import com.greenturret.repository.UserRepository;
import com.greenturret.util.UserValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.List;

import static org.springframework.security.config.Elements.JWT;

@Service
public class UserService {

    private final UserRepository userRepository;

    private static final long TEN_DAYS =  864_000_000;

    @Value("${jwt.secret")
    private String jwtsecret;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser(UserDTO userDTO) {
        if (usernameExist(userDTO.getUsername())) {
            throw new UsernameAlreadyExistsException(ErrorConsts.USERNAME_ALREADY_EXISTS);
        }

        UserValidationUtil.validateUser(userDTO); // Handle user validation

        User newUser=  new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(newUser);
    }

    private boolean usernameExist(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    // In UserService.java
    public String authenticateUser(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(ErrorConsts.USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(ErrorConsts.INVALID_CREDENTIALS);
        }

        if(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            // User is authenticated, return a token
            return generateToken(user);
        } else {
            throw new BadCredentialsException(ErrorConsts.INVALID_CREDENTIALS);
        }
    }

    private String generateToken(User user) {
        long expirationTime = TEN_DAYS;
        return com.auth0.jwt.JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
               .sign(Algorithm.HMAC512(jwtsecret));
    }
}
