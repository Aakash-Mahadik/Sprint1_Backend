package com.springboot.taskmanager.service;


import java.util.List;
import java.util.Optional;

import com.springboot.taskmanager.entity.Task;
import com.springboot.taskmanager.entity.User;

public interface UserService {
    Optional<User> getUserById(Long userId);
    
    public Optional<User> getUserByUsername(String username);


    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long userId);

    List<Task> getUserTasks(Long userId);

    Task getUserTask(Long userId, Long taskId);
    
    
}
