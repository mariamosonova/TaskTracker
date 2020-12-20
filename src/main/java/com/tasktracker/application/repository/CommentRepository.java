package com.tasktracker.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tasktracker.application.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
  List<Comment> findByTaskId(String taskId);

}

