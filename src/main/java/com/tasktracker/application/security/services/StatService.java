package com.tasktracker.application.security.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasktracker.application.models.Bonus;
import com.tasktracker.application.models.Salary;
import com.tasktracker.application.models.Task;
import com.tasktracker.application.models.User;
import com.tasktracker.application.repository.BonusRepository;
import com.tasktracker.application.repository.TaskRepository;
import com.tasktracker.application.repository.UserRepository;
import com.tasktracker.application.security.jwt.JwtUtils;
import com.tasktracker.application.security.services.UserDetailsImpl;
import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

@Component
public class StatService {


    private Date convToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private LocalDate convToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public boolean isDatesInRange(String month, String year, Date endDate, Date startDate, Long points)
    {
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(year), Month.of(Integer.parseInt(month)));

        LocalDate firstOfMonth = yearMonth.atDay(1);
        LocalDate last = yearMonth.atEndOfMonth();
        
        LocalDate endDateMinusPoints = convToLocalDate(endDate).minusDays(points);
        LocalDate startDatePlusPoints = convToLocalDate(startDate).plusDays(points);


        return convToDate(endDateMinusPoints).after(convToDate(firstOfMonth))
                && convToDate(endDateMinusPoints).before(convToDate(last))
                || convToDate(startDatePlusPoints).after(convToDate(firstOfMonth))
                        && convToDate(startDatePlusPoints).before(convToDate(last));
    }
}
