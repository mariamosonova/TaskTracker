package com.tasktracker.application;

import com.tasktracker.application.models.ERole;
import com.tasktracker.application.models.Role;
import com.tasktracker.application.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = new User ("testLogin",
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                "100000");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {
        assertEquals(null, user.getId());
    }

    @Test
    void setId() {
        user.setId((long) 2);
        assertEquals(2, user.getId());
    }

    @Test
    void getUsername() {
        assertEquals("testLogin", user.getUsername());
    }

    @Test
    void setUsername() {
        user.setUsername("newTestLogin");
        assertEquals("newTestLogin", user.getUsername());
    }

    @Test
    void getFirstname() {
        assertEquals("testFirstName", user.getFirstname());
    }

    @Test
    void setFirstname() {
        user.setFirstname("newTestFirstName");
        assertEquals("newTestFirstName", user.getFirstname());
    }

    @Test
    void getLastname() {
        assertEquals("testLastName", user.getLastname());
    }

    @Test
    void setLastname() {
        user.setLastname("newTestLastName");
        assertEquals("newTestLastName", user.getLastname());
    }

    @Test
    void getEmail() {
        assertEquals("testEmail", user.getEmail());
    }

    @Test
    void setEmail() {
        user.setEmail("newTestEmail");
        assertEquals("newTestEmail", user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    void setPassword() {
        user.setPassword("newTestPassword");
        assertEquals("newTestPassword", user.getPassword());
    }

    @Test
    void getRoles() {
        assertEquals(new HashSet<Role>(), user.getRoles());
    }

    @Test
    void setRoles() {
        Set<Role> roles = new HashSet<Role>();
        roles.add(new Role(ERole.ROLE_USER));
        user.setRoles(roles);
        assertEquals(roles, user.getRoles());
    }

    @Test
    void getBaseSalary() {
        assertEquals("100000", user.getBaseSalary());
    }

    @Test
    void setBaseSalary() {
        user.setBaseSalary("500000");
        assertEquals("500000", user.getBaseSalary());
    }
}
