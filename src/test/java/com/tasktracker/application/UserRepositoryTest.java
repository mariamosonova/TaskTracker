package com.tasktracker.application;

import com.tasktracker.application.models.User;
import com.tasktracker.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest

class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findByUsername() {
        User user = new User ("testLogin",
                "testFirstName",
                "testLastName",
                "testEmail@ya.ru",
                "testPassword",
                "100000");
        userRepository.save(user);
        Optional<?> queryResult = userRepository.findByUsername("testLogin");
        assertFalse(!queryResult.isPresent());
        userRepository.deleteById(user.getId());

    }

    @Test
    void existsByEmail() {
        User user = new User ("testLogin",
                "testFirstName",
                "testLastName",
                "testEmail@ya.ru",
                "testPassword",
                "100000");
        userRepository.save(user);
        boolean isExist = userRepository.existsByEmail("testEmail@ya.ru");
        assertEquals(true, isExist);
        userRepository.deleteById(user.getId());

    }

    @Test
    void existsByUsername() {
        User user = new User ("testLogin",
                "testFirstName",
                "testLastName",
                "testEmail@ya.ru",
                "testPassword",
                "100000");
        userRepository.save(user);
        boolean isExist = userRepository.existsByUsername("testLogin");
        assertEquals(true, isExist);
        userRepository.deleteById(user.getId());
    }
}