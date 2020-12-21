package com.tasktracker.application;

import com.tasktracker.application.models.Task;
import com.tasktracker.application.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
                false,
                "New");
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
                false,
                "New");
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
                false,
                "New");
        taskRepository.save(task);
        List<?> queryResult = taskRepository.findByAssigned("testUser");
        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));
    }
}