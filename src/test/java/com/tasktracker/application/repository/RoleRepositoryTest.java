package com.tasktracker.application;

import org.junit.jupiter.api.Test;

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
        List<?> queryResult = roleRepository.findByName(ERole.ROLE_USER);
        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));

    }
}