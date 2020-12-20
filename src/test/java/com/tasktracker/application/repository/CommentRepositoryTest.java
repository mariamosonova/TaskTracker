package com.tasktracker.application;

import org.junit.jupiter.api.Test;

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
        List<?> queryResult = repository.findByTaskId("12");
        assertFalse(queryResult.isEmpty());
        assertNotNull(queryResult.get(0));

    }
}