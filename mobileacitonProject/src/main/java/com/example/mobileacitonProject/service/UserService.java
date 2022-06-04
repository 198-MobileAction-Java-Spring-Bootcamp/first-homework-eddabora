package com.example.mobileacitonProject.service;

import com.example.mobileacitonProject.model.User;

import java.util.List;

public interface UserService {
    void register(User user);

    void updateUser(Long userId, User user);

    List<User> findAll();

    User findById(Long id);

    User updateUserStatus(User user);

    String deleteUser(Long id);
    
}
