package com.tasktracker.application;

import org.junit.jupiter.api.Test;

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
                "testEmail",
                "testPassword");
        userRepository.save(user);
        List<?> queryResult = userRepository.findByUsername("testLogin");
        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));
    }

    @Test
    void existsByEmail() {
        User user = new User ("testLogin",
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword");
        userRepository.save(user);
        boolean isExist = userRepository.existsByEmail("testEmail");
        assertEquals(true, isExist);

    }

    @Test
    void existsByUsername() {
        User user = new User ("testLogin",
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword");
        userRepository.save(user);
        boolean isExist = userRepository.existsByUsername("testLogin");
        assertEquals(true, isExist);
    }
}