package com.tasktracker.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;
import com.tasktracker.application.security.services.UserDetailsImpl;

import com.tasktracker.application.models.Task;
import com.tasktracker.application.links.TaskLinks;
import com.tasktracker.application.security.services.TaskService;
import com.tasktracker.application.repository.TaskRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tasktracker.application.models.ERole;
import com.tasktracker.application.models.Role;
import com.tasktracker.application.models.User;
import com.tasktracker.application.payload.request.LoginRequest;
import com.tasktracker.application.payload.request.SignupRequest;
import com.tasktracker.application.payload.response.JwtResponse;
import com.tasktracker.application.payload.response.MessageResponse;
import com.tasktracker.application.repository.RoleRepository;
import com.tasktracker.application.repository.UserRepository;
import com.tasktracker.application.security.jwt.JwtUtils;
import com.tasktracker.application.security.services.UserDetailsImpl;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @PostMapping("/tasks/create-comment")//?
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        try {
            Comment _comment = commentRepository
                    .save(new Comment(comment.getText(), task.getUser(), task.getTask(), task.getCreatedAt()));
            return new ResponseEntity<>(_comment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tasks/update-comment/{comment_id}")//?
    public ResponseEntity<Task> updateTask(@PathVariable("comment_id") long comment_id, @RequestBody Comment comment) {
        Optional<Comment> commentData = commentRepository.findById(comment_id);

        if (commentData.isPresent()) {
            Comment _comment = commentData.get();
            if(_comment.getUser() != commentData.getUser()) {
                //TODO: дописать
            }
            _comment.setText(task.getTaskTitle());
            _comment.setUpdatedAt(OffsetDateTime.now());
            return new ResponseEntity<>(taskRepository.save(_comment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tasks/delete-comment/{comment_id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("comment_id") long comment_id) {
        try {
            taskRepository.deleteById(comment_id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}