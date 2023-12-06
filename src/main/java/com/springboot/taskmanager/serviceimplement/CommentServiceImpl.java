package com.springboot.taskmanager.serviceimplement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.taskmanager.entity.Comment;
import com.springboot.taskmanager.entity.Task;
import com.springboot.taskmanager.exception.EntityNotFoundException;
import com.springboot.taskmanager.repository.CommentRepository;
import com.springboot.taskmanager.repository.TaskRepository;
import com.springboot.taskmanager.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment", commentId));
    }

    @Override
    public List<Comment> getCommentsByTaskId(Long taskId) {
        // Implement logic to get comments by task ID
        return commentRepository.findByTask_TaskId(taskId);
    }

    @Override
    public Comment createComment(Comment comment) {
        // Implement comment creation logic (if needed)
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        if (!commentRepository.existsById(comment.getCommentId())) {
            throw new EntityNotFoundException("Comment", comment.getCommentId());
        }
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public Task getCommentTask(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment", commentId));

        return comment.getTask();
    }
}

