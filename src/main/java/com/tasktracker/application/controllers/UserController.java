package com.tasktracker.application.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.LocalDate;

import com.tasktracker.application.payload.request.LoginRequest;
import com.tasktracker.application.payload.request.SignupRequest;
import com.tasktracker.application.payload.response.JwtResponse;
import com.tasktracker.application.payload.response.MessageResponse;
import com.tasktracker.application.repository.RoleRepository;
import com.tasktracker.application.repository.UserRepository;
import com.tasktracker.application.security.jwt.JwtUtils;
import com.tasktracker.application.security.services.StatService;
import com.tasktracker.application.security.services.TaskService;
import com.tasktracker.application.security.services.UserDetailsImpl;
import com.tasktracker.application.models.Task;
import com.tasktracker.application.models.User;
import com.tasktracker.application.models.UserView;
import com.tasktracker.application.links.UserLinks;
import com.tasktracker.application.security.services.UserService;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    @Autowired
    StatService statService;

    @GetMapping(path = UserLinks.LIST_USERS)
    public ResponseEntity<?> listUsers() throws ParseException {
        log.info("UsersController:  list users");
        List<User> resource = userService.getUsers();
        List<UserView> userView = new ArrayList<UserView>();
        for (User user : resource) {

            List<Task> taskData = taskService.findByResolvedAndAssigned(true, user.getUsername());
            Float efficiency = 0f;
            for (Task task : taskData) {
    
                Long points = Long.parseLong(task.getPoints());
    
                // date store like yyyy-MM-dd
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
                Date endDate = sdf.parse(StringUtils.substring(task.getEta(), 0, 10));
                Date startDate = sdf.parse(StringUtils.substring(task.getStartDate(), 0, 10));
    
                if (statService.isDatesInRange(String.valueOf(LocalDate.now().getMonthValue()), String.valueOf(LocalDate.now().getYear()), endDate, startDate, points)) {
                    Long estimatedDays = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
                    efficiency += estimatedDays.floatValue() / points.floatValue();
                }
            }

            if(!taskData.isEmpty())
                efficiency /= taskData.size();

            if(efficiency == 0) efficiency = 1f;

            userView.add(new UserView(user, efficiency));
        }

        return ResponseEntity.ok(userView);
    }

    @RequestMapping(value = UserLinks.UPDATE_USER, method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MODERATOR')")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        log.info("UsersController:  update user");
        User resource = userService.updateUser(user);
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(value = UserLinks.DELETE_USER, method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@RequestBody User user) {
        log.info("UsersController:  delete user");
        userService.deleteUser(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MODERATOR')")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) {

        User user = userService.getUser(id);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserById(@PathVariable("username") String username) {

        User user = userService.getUserByUsername(username);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
