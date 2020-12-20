package com.tasktracker.application;

import com.tasktracker.application.models.ERole;
import com.tasktracker.application.models.Role;
import com.tasktracker.application.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest

class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @Test
    void findByName() {

        Role role = new Role(ERole.ROLE_USER);
        roleRepository.save(role);
        Optional<?> queryResult = roleRepository.findByName(ERole.ROLE_USER);
        assertFalse(!queryResult.isPresent());

    }
}