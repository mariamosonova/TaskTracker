package com.tasktracker.application;

import com.tasktracker.application.models.AttributeType;
import com.tasktracker.application.models.EAttributeType;
import com.tasktracker.application.models.TaskAttribute;
import com.tasktracker.application.repository.TaskAttributeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskAttributeRepositoryTest {

    @Autowired
    TaskAttributeRepository taskAttributeRepository;

    @Test
    void findByTaskId() {
        TaskAttribute taskAttribute = new TaskAttribute(
                "some test",
                "23",
                new AttributeType(EAttributeType.TYPE_STRING));

        taskAttributeRepository.save(taskAttribute);
        List<?> queryResult = taskAttributeRepository.findByTaskId("23");
        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));
        taskAttributeRepository.deleteById(taskAttribute.getAttributeId());
    }
}