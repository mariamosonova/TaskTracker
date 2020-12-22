package com.tasktracker.application.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comments")
public class Comment {

  @Id
  @Column // To be remove ?
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long comment_id;

  @NotBlank
  @Column(name = "userId")
  private String userId;

  @NotBlank
  @Column(name = "taskId")
  private String taskId;

  @NotBlank
  @Column(name = "comment")
  private String comment;

  @NotBlank
  @Column(name = "date")
  private String date;

  public Comment() {}

  public Comment(String userId, String taskId, String comment, String date) {
    this.userId = userId;
    this.taskId = taskId;
    this.comment = comment;
    this.date = date;
  }

  public Long getId() {
    return comment_id;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
