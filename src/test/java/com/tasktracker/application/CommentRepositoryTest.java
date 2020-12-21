package com.tasktracker.application;

import com.tasktracker.application.models.Comment;
import com.tasktracker.application.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest

class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void findByTaskId() {

        Comment comment = new Comment("1",
                "12",
                "test comment",
                "2020-12-12");

        commentRepository.save(comment);
        List<?> queryResult = commentRepository.findByTaskId("12");
        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));
        commentRepository.deleteAll();

    }
}