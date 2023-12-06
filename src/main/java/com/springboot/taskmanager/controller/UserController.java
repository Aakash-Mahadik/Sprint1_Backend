package com.springboot.taskmanager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.springboot.taskmanager.entity.Task;
import com.springboot.taskmanager.entity.User;
import com.springboot.taskmanager.exception.EntityNotFoundException;
import com.springboot.taskmanager.exception.InvalidRequestException;
import com.springboot.taskmanager.service.UserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseThrow(() -> new EntityNotFoundException("User", userId));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser( @RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId,  @RequestBody User user) {
        if (!userId.equals(user.getUserId())) {
            throw new InvalidRequestException("User ID in path must match User ID in request body");
        }
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    // Additional Endpoints

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long userId) {
        List<Task> tasks = userService.getUserTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{userId}/tasks/{taskId}")
    public ResponseEntity<Task> getUserTask(@PathVariable Long userId, @PathVariable Long taskId) {
        Task task = userService.getUserTask(userId, taskId);
        if (task == null) {
            throw new EntityNotFoundException("Task", taskId);
        }
        return ResponseEntity.ok(task);
    }
}
