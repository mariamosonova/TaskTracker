package com.tasktracker.application.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task;
    Comment comment;

    @BeforeEach
    void setUp() {
        task = new Task("test Title",
                "test description",
                "2020-12-13",
                "2020-12-10",
                "testUser",
                "5",
                false);
        comment = new Comment("1",
                "12",
                "test comment",
                "2020-12-12");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getComments() {
        assertEquals(new LinkedList<Role>(), task.getComments());
    }

    @Test
    void setComments() {
        List<Comment> comments = new LinkedList<Comment>();
        comments.add(comment);
        task.setComments(comments);
        assertEquals(comments, task.getComments());
    }

    @Test
    void getId() {
    }

    @Test
    void getTaskTitle() {
        assertEquals("test Title", task.getTaskTitle());
    }

    @Test
    void setTaskTitle() {
        task.setTaskTitle("test new Title");
        assertEquals("test new Title", task.getTaskTitle());
    }

    @Test
    void getTaskDescription() {
        assertEquals("test description", task.getTaskDescription());
    }

    @Test
    void setTaskDescription() {
        task.setTaskDescription("test new description");
        assertEquals("test new description", task.getTaskDescription());
    }

    @Test
    void getEta() {
        assertEquals("2020-12-13", task.getEta());
    }

    @Test
    void setEta() {
        task.setEta("2020-12-15");
        assertEquals("2020-12-15", task.getEta());
    }

    @Test
    void getStartDate() {
        assertEquals("2020-12-10", task.getStartDate());
    }

    @Test
    void setStartDate() {
        task.setStartDate("2020-12-12");
        assertEquals("2020-12-12", task.getStartDate());
    }

    @Test
    void getAssigned() {
        assertEquals("testUser", task.getAssigned());
    }

    @Test
    void setAssigned() {
        task.setAssigned("newTestUser");
        assertEquals("newTestUser", task.getAssigned());
    }

    @Test
    void getPoints() {
        assertEquals("5", task.getPoints());
    }

    @Test
    void setPoints() {
        task.setPoints("6");
        assertEquals("6", task.getPoints());
    }

    @Test
    void isResolved() {
        assertEquals(false, task.isResolved());
    }

    @Test
    void setResolved() {
        task.setResolved(true);
        assertEquals(true, task.isResolved());
    }
}