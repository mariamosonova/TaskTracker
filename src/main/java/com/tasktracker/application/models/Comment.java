package com.tasktracker.application.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "text")
    @NotNull(message="{NotNull.Comment.text}")
    private String text;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @NotNull(message="{NotNull.Comment.created_at}")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @NotNull(message="{NotNull.Comment.updated_at}")
    private OffsetDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {
    }

    public Comment(String text, User user, Task task) {
        this.text = text;
        this.user = user;
        this.task = task;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now()
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Comment [comment_id=" + comment_id + ", text=" + text + ", createdAt=" + createdAt.toString() + ", user_id=" +  user.getId() "]";
    }
}