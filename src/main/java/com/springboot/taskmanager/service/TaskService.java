package com.springboot.taskmanager.service;


import java.util.List;

import com.springboot.taskmanager.entity.Comment;
import com.springboot.taskmanager.entity.Task;

public interface TaskService {
	
	 List<Task> getAllTasks();
	 
    Task getTaskById(Long taskId);

    List<Task> getTasksByUserId(Long userId);

    Task createTask(Task task);

    Task updateTask(Task task);

    void deleteTask(Long taskId);

    List<Comment> getTaskComments(Long taskId);

    Comment addCommentToTask(Long taskId, Comment comment);
    
    List<Task> getTasksWithUserDetails();
}
