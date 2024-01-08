package com.greenturret.service;

import com.greenturret.model.User;
import com.greenturret.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setId("1");
        user1.setUsername("user1name");
        user1.setRole("donor");

        User user2 = new User();
        user2.setId("2");
        user2.setUsername("user2name");
        user2.setRole("driver");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

// Act
        List<User> users = userService.getAllUsers();

// Assert
        assertEquals(2, users.size());
// Additional assertions can be added to validate the users

    }
}
