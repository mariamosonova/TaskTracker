package com.tasktracker.application.controllers;

import com.tasktracker.application.links.TaskAttributeLinks;
import com.tasktracker.application.models.*;
import com.tasktracker.application.payload.response.MessageResponse;
import com.tasktracker.application.repository.TaskAttributeRepository;
import com.tasktracker.application.security.services.TaskAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TaskAttributeController {

  @Autowired
  TaskAttributeService taskAttributeService;

  @Autowired
  TaskAttributeRepository taskAttributeRepository;

  @PostMapping(path = TaskAttributeLinks.ADD_TASK_ATTRIBUTE)
  public ResponseEntity<?> addTaskAttribute(@RequestBody TaskAttribute taskAttribute) {
    try {
      taskAttributeService.saveTaskAttribute(taskAttribute);
      return new ResponseEntity<>(new MessageResponse("Task attribute has been added successfully!"), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping(path = TaskAttributeLinks.DELETE_TASK_ATTRIBUTE)
  public ResponseEntity<HttpStatus> deleteTaskAttribute(
          @RequestParam("id") long id) {
    try {
      TaskAttribute taskAttribute = taskAttributeService.getTaskAttribute(id);

      if (taskAttribute == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      taskAttributeRepository.delete(taskAttribute);

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
