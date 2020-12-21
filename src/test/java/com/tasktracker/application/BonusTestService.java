package com.tasktracker.application;

import com.tasktracker.application.DTO.BonusCalculationDTO;
import com.tasktracker.application.models.Task;
import com.tasktracker.application.models.User;
import com.tasktracker.application.repository.TaskRepository;
import com.tasktracker.application.repository.UserRepository;
import com.tasktracker.application.security.services.BonusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.ParseException;

import java.time.YearMonth;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BonusTestService {

    @Autowired
    BonusService bonusService;

    @BeforeEach
    void setUp() {
        User user = new User("testLogin",
                "testFirstName",
                "testLastName",
                "testEmail",
                "testPassword",
                "100000");

        Task task1 = new Task(
                "1 task",
                "ouoyoyooyooy",
                "2020-12-15",
                "2020-12-01",
                "YOLO",
                "87",
                true,
                "done");

        Task task2 = new Task(
                "2 task",
                "ouoyoyooyooy",
                "2020-12-22",
                "2020-12-11",
                "YOLO",
                "15",
                false,
                "in progress");

        Task task3 = new Task(
                "3 task",
                "ouoyoyooyooy",
                "2020-12-10",
                "2020-12-01",
                "YOLO",
                "10",
                true,
                "done");

        Task task4 = new Task(
                "4 task",
                "ouoyoyooyooy",
                "2020-12-31",
                "2020-12-01",
                "YOLO",
                "32",
                false,
                "done");


        List<User> users = Collections.singletonList(user);
        List<Task> tasks = new LinkedList<Task>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
        Mockito.when(taskRepository.findByAssigned(user.getUsername())).thenReturn(tasks);
        bonusService = new BonusService();
        bonusService.setUserRepository(userRepository);
        bonusService.setTaskRepository(taskRepository);

    }
    @Test
    void testCalculateBonus() throws ParseException {

        Float coef = 0.5f;
        YearMonth yearMonth = YearMonth.of(2020, 12);
        List<BonusCalculationDTO> dto = bonusService.calculateBonus(yearMonth,coef);
        assertEquals(true, dto.get(0).isAssignBonus());
    }
}
