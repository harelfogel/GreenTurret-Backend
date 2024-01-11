package com.greenturret.service;

import com.greenturret.dto.UserDTO;
import com.greenturret.exception.UsernameAlreadyExistsException;
import com.greenturret.model.User;
import com.greenturret.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

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

        if(usernameExist(userDTO.getUsername())){
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        User newUser=  new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(newUser);
    }

    private boolean usernameExist(String username){

        return userRepository.findByUsername(username).isPresent();
    }

    // Other methods like createUser, getUserById, updateUser, deleteUser...
}
