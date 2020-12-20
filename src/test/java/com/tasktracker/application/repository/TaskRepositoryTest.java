package com.tasktracker.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest

class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void findByResolved() {
        Task task = new Task("test Title",
                "test description",
                "2020-12-13",
                "2020-12-10",
                "testUser",
                "5",
                false);
        taskRepository.save(task);
        List<?> queryResult = taskRepository.findByResolved(false);
        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));
    }

    @Test
    void findByTaskTitleContaining() {
        Task task = new Task("test Title",
                "test description",
                "2020-12-13",
                "2020-12-10",
                "testUser",
                "5",
                false);
        taskRepository.save(task);
        List<?> queryResult = taskRepository.findByTaskTitleContaining("test Title");
        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));
    }

    @Test
    void findByAssigned() {
        Task task = new Task("test Title",
                "test description",
                "2020-12-13",
                "2020-12-10",
                "testUser",
                "5",
                false);
        taskRepository.save(task);
        List<?> queryResult = taskRepository.findByAssigned("testUser");
        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));
    }
}