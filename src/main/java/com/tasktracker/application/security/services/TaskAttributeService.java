package com.tasktracker.application.security.services;

import com.tasktracker.application.models.AttributeType;
import com.tasktracker.application.models.Task;
import com.tasktracker.application.models.TaskAttribute;
import com.tasktracker.application.repository.AttributeTypeRepository;
import com.tasktracker.application.repository.TaskAttributeRepository;
import com.tasktracker.application.repository.TaskRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskAttributeService {

  private TaskAttributeRepository taskAttributeRepository;

  private AttributeTypeRepository attributeTypeRepository;

  private TaskRepository taskRepository;

  public TaskAttributeService(TaskAttributeRepository taskAttributeRepository, AttributeTypeRepository attributeTypeRepository, TaskRepository taskRepository) {
    this.taskAttributeRepository = taskAttributeRepository;
    this.attributeTypeRepository = attributeTypeRepository;
    this.taskRepository = taskRepository;
  }

  public List<TaskAttribute> getTaskAttributeByTask(Task task) {
    return taskAttributeRepository.findByTaskId(Float.toString(task.getId()));
  }

  public TaskAttribute saveTaskAttribute(TaskAttribute taskAttribute) throws NotFoundException {

    if(!taskRepository.findById(Long.parseLong(taskAttribute.getTaskId())).isPresent()) {
      throw new NotFoundException("Task was not found by id " + taskAttribute.getTaskId());
    }

    Optional<AttributeType> attributeType = attributeTypeRepository.findByName(taskAttribute.getType().getName());
    if(!attributeType.isPresent()) {
      attributeTypeRepository.save(taskAttribute.getType());
    } else {
      taskAttribute.setType(attributeType.get());
    }
    return taskAttributeRepository.save(taskAttribute);
  }

  public void deleteTaskAttribute(TaskAttribute taskAttribute) {
    taskAttributeRepository.delete(taskAttribute);
  }

  public TaskAttribute getTaskAttribute(Long Id) {
    return taskAttributeRepository.findById(Id).orElse(null);
  }
}
