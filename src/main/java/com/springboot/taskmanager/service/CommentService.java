package com.springboot.taskmanager.service;


import java.util.List;

import com.springboot.taskmanager.entity.Comment;
import com.springboot.taskmanager.entity.Task;

public interface CommentService {
    Comment getCommentById(Long commentId);

    List<Comment> getCommentsByTaskId(Long taskId);

    Comment createComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(Long commentId);

    Task getCommentTask(Long commentId);
}
