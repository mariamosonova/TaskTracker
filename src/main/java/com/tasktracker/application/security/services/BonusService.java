package com.tasktracker.application.security.services;

import com.tasktracker.application.DTO.BonusCalculationDTO;
import com.tasktracker.application.helpers.Formatter;
import com.tasktracker.application.models.Task;
import com.tasktracker.application.models.User;
import com.tasktracker.application.repository.TaskRepository;
import com.tasktracker.application.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class BonusService {


    private UserRepository userRepository;

    private TaskRepository taskRepository;



    public List<BonusCalculationDTO> calculateBonus(YearMonth yearMonth, float coef) throws ParseException {
        List<User> userList = userRepository.findAll();

        if (userList.isEmpty()) {
            throw new RuntimeException();
        }

        List<BonusCalculationDTO> dtoList = new LinkedList<BonusCalculationDTO>();
        for (User user : userList) {

            List<Task> allUserTasks = taskRepository.findByAssigned(user.getUsername());

            if (allUserTasks.isEmpty()) {
                BonusCalculationDTO dto = new BonusCalculationDTO().setUser(user);
                dtoList.add(dto);
                continue;
            }

            Float summaryEstimationCoef = 0f;
            LocalDate firstOfMonth = yearMonth.atDay(1);
            LocalDate last = yearMonth.atEndOfMonth();

            for (Task task : allUserTasks) {
                Long points = Long.parseLong(task.getPoints());

                // date store like yyyy-MM-dd
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Date endDate = sdf.parse(StringUtils.substring(task.getEta(), 0, 10));
                LocalDate endDateMinusPoints = Formatter.toLocalDate(endDate).minusDays(points);

                Date startDate = sdf.parse(StringUtils.substring(task.getStartDate(), 0, 10));
                LocalDate startDatePlusPoints = Formatter.toLocalDate(startDate).plusDays(points);

                if ((Formatter.toDate(endDateMinusPoints).after(Formatter.toDate(firstOfMonth))
                        && Formatter.toDate(endDateMinusPoints).before(Formatter.toDate(last)))
                        || (Formatter.toDate(startDatePlusPoints).after(Formatter.toDate(firstOfMonth))
                        && Formatter.toDate(startDatePlusPoints).before(Formatter.toDate(last)))
                ) {
                    Long estimatedDays = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
                    Float estimationCoef = estimatedDays.floatValue() / points.floatValue();

                    summaryEstimationCoef += estimationCoef;
                }
            }
            BonusCalculationDTO dto = new BonusCalculationDTO()
                    .setUser(user)
                    .setEfficiency(Float.toString(summaryEstimationCoef))
                    .setAssignBonus(summaryEstimationCoef > coef);

            dtoList.add(dto);
        }
        return dtoList;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
