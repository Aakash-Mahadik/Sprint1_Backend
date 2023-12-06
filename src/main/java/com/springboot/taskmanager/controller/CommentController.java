package com.springboot.taskmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.springboot.taskmanager.entity.Comment;
import com.springboot.taskmanager.entity.Task;
import com.springboot.taskmanager.exception.EntityNotFoundException;
import com.springboot.taskmanager.exception.InvalidRequestException;
import com.springboot.taskmanager.service.CommentService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long commentId) {
        Comment comment = commentService.getCommentById(commentId);
        if (comment == null) {
            throw new EntityNotFoundException("Comment", commentId);
        }
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Comment>> getCommentsByTaskId(@PathVariable Long taskId) {
        List<Comment> comments = commentService.getCommentsByTaskId(taskId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<Comment> createComment( @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {
        if (!commentId.equals(comment.getCommentId())) {
            throw new InvalidRequestException("Comment ID in path must match Comment ID in request body");
        }
        return ResponseEntity.ok(commentService.updateComment(comment));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    // Additional Endpoints

    @GetMapping("/{commentId}/task")
    public ResponseEntity<Task> getCommentTask(@PathVariable Long commentId) {
        Task task = commentService.getCommentTask(commentId);
        if (task == null) {
            throw new EntityNotFoundException ("Task" + "associated with Comment", commentId);
            //throw new EntityNotFoundException ("Task", "associated with Comment", commentId); this is actual upper change bcoz it giving error
        }
        return ResponseEntity.ok(task);
    }
}

