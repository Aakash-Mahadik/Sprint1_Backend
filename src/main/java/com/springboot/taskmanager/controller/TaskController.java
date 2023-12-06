package com.springboot.taskmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.springboot.taskmanager.entity.Comment;
import com.springboot.taskmanager.entity.Task;
import com.springboot.taskmanager.entity.User;
import com.springboot.taskmanager.exception.EntityNotFoundException;
import com.springboot.taskmanager.exception.InvalidRequestException;
import com.springboot.taskmanager.exception.UserNotFoundExceptionByUsername;
import com.springboot.taskmanager.service.TaskService;
import com.springboot.taskmanager.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private UserService userService;
	
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        if (task == null) {
            throw new EntityNotFoundException("Task", taskId);
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> createTask( @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        if (!taskId.equals(task.getTaskId())) {
            throw new InvalidRequestException("Task ID in path must match Task ID in request body");
        }
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    // Additional Endpoints

    @GetMapping("/{taskId}/comments")
    public ResponseEntity<List<Comment>> getTaskComments(@PathVariable Long taskId) {
        List<Comment> comments = taskService.getTaskComments(taskId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{taskId}/comments")
    public ResponseEntity<Comment> addCommentToTask(@PathVariable Long taskId,@RequestBody Comment comment) {
        return ResponseEntity.ok(taskService.addCommentToTask(taskId, comment));
    }
    
    
    @PostMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        Task task = taskService.getTaskById(taskId);
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId));

        task.setUser(user);
        Task updatedTask = taskService.updateTask(task);

        return ResponseEntity.ok(updatedTask);
    }

    
//    @PostMapping("/{taskId}/assign/{username}")
//    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId, @PathVariable String username) {
//        Task task = taskService.getTaskById(taskId);
//
//        // Retrieve the user by username from the UserService
//        User user = userService.getUserByUsername(username)
//                .orElseThrow(() -> new UserNotFoundExceptionByUsername("User", "username", username));
//
//        // Assign the task to the user
//        task.setUser(user);
//
//        // Update the task with the new user assignment
//        taskService.updateTask(task);
//
//        return ResponseEntity.ok(task);
//    }

    
    


}
