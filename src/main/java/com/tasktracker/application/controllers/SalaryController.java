package com.tasktracker.application.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasktracker.application.models.Bonus;
import com.tasktracker.application.models.Salary;
import com.tasktracker.application.models.Task;
import com.tasktracker.application.models.User;
import com.tasktracker.application.repository.BonusRepository;
import com.tasktracker.application.repository.TaskRepository;
import com.tasktracker.application.repository.UserRepository;
import com.tasktracker.application.security.services.StatService;


import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Slf4j
@RequestMapping("/api")
public class SalaryController {

    @Autowired
    BonusRepository bonusRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    StatService statService;


    @GetMapping("/salary/{user_id}/{month}/{year}")
    public ResponseEntity<?> getUserSalaryByMonth(@PathVariable("user_id") String user_id,
            @PathVariable("month") String month, @PathVariable("year") String year) throws ParseException {
        List<Bonus> bonusData = bonusRepository.findByUserIdAndMonthAndYear(user_id, month, year);
        User userData = userRepository.findById(Long.parseLong(user_id)).get();
        List<Task> taskData = taskRepository.findByResolvedAndAssigned(true, userData.getUsername());

        Float baseSalary = Float.parseFloat(userData.getBaseSalary());
        Float fullSalary = baseSalary;

        Float aveEff = 0f;


        for (Task task : taskData) {
            Long points = Long.parseLong(task.getPoints());

            // date store like yyyy-MM-dd
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date endDate = sdf.parse(StringUtils.substring(task.getEta(), 0, 10));

            Date startDate = sdf.parse(StringUtils.substring(task.getStartDate(), 0, 10));

            if (statService.isDatesInRange(month, year, endDate, startDate, points)) {
                Long estimatedDays = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
                Float estimationCoef = estimatedDays.floatValue() / points.floatValue();

                aveEff += estimationCoef;

                if (estimationCoef >= 1)
                    fullSalary += 1000 * estimationCoef;
            }
        }

        if(!taskData.isEmpty())
            aveEff /= taskData.size();

        if(aveEff == 0) aveEff = 1f;

        for (Bonus bonus : bonusData) {
            fullSalary += Float.parseFloat(bonus.getAmount());
        }

        return new ResponseEntity<>(
                new Salary(userData.getLastname() + ", " + userData.getFirstname(), fullSalary.toString(), aveEff.toString()),
                HttpStatus.OK);
    }
}
