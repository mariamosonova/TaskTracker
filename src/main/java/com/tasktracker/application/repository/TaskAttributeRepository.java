package com.tasktracker.application.repository;

import com.tasktracker.application.models.TaskAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskAttributeRepository extends JpaRepository<TaskAttribute, Long> {
  List<TaskAttribute> findByTaskId(String taskId);
  Optional<TaskAttribute> findById(Long Id);
}
