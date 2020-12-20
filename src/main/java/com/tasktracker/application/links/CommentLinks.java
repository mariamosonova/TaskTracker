package com.tasktracker.application.links;

import org.springframework.stereotype.Component;

@Component
public class CommentLinks {
    public static final String GET_COMMENTS = "/get_comments";
    public static final String ADD_COMMENT = "/add_comment";
    public static final String UPDATE_COMMENT = "/update_comment";
    public static final String DELETE_COMMENT = "/delete_comment";
}
