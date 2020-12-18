package com.tasktracker.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tasktracker.application.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Comment> findByTaskId(long taskId);
    List<Comment> findById(long Id);

}