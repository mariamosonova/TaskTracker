package com.tasktracker.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    Comment comment;

    @BeforeEach
    void setUp() {
        comment = new Comment("1",
                "12",
                "test comment",
                "2020-12-12");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {

    }

    @Test
    void getTaskId() {
        assertEquals("12", comment.getTaskId());
    }

    @Test
    void setTaskId() {
        comment.setTaskId("123");
        assertEquals("123", comment.getTaskId());
    }

    @Test
    void getUserId() {
        assertEquals("1", comment.getUserId());
    }

    @Test
    void setUserId() {
        comment.setUserId("2");
        assertEquals("2", comment.getUserId());
    }

    @Test
    void getComment() {
        assertEquals("test comment", comment.getComment());
    }

    @Test
    void setComment() {
        comment.setComment("new test comment");
        assertEquals("new test comment", comment.getComment());
    }

    @Test
    void getDate() {
        assertEquals("2020-12-12", comment.getDate());
    }

    @Test
    void setDate() {
        comment.setDate("2020-12-13");
        assertEquals("2020-12-13", comment.getDate());
    }
}
