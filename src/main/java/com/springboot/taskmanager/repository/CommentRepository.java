package com.springboot.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.taskmanager.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask_TaskId(Long taskId);
}