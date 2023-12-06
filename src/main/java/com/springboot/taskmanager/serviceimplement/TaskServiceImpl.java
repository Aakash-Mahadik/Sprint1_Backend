package com.springboot.taskmanager.serviceimplement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.taskmanager.entity.Comment;
import com.springboot.taskmanager.entity.Task;
import com.springboot.taskmanager.exception.EntityNotFoundException;
import com.springboot.taskmanager.repository.CommentRepository;
import com.springboot.taskmanager.repository.TaskRepository;
import com.springboot.taskmanager.service.TaskService;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

	
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task", taskId));
    }

    @Override
    public List<Task> getTasksByUserId(Long userId) {
        // Implement logic to get tasks by user ID
        return taskRepository.findByUser_Id(userId);
    }

    @Override
    public Task createTask(Task task) {
        // Implement task creation logic (if needed)
        return taskRepository.save(task);
    }

    @Transactional
    @Override
    public Task updateTask(Task task) {
        if (!taskRepository.existsById(task.getTaskId())) {
            throw new EntityNotFoundException("Task", task.getTaskId());
        }
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<Comment> getTaskComments(Long taskId) {
        return commentRepository.findByTask_TaskId(taskId);
    }

    @Override
    public Comment addCommentToTask(Long taskId, Comment comment) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task", taskId));

        comment.setTask(task);
        return commentRepository.save(comment);
    }
    
//    @Override
//    public List<Task> getTasksWithUserDetails() {
//        return taskRepository.findAll(); // This will now fetch tasks with user details.
//    }
    
    @Override
    public List<Task> getTasksWithUserDetails() {
        return taskRepository.findAllWithUserDetails();
    }

}

