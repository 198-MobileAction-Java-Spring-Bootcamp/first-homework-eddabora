package com.example.mobileacitonProject.controller;

import com.example.mobileacitonProject.model.User;
import com.example.mobileacitonProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) throws Exception {
        userService.register(user);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable Long userId,
                                    @RequestBody User user) throws Exception {

        userService.updateUser(userId, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public List<User> findAll() throws Exception {
        return userService.findAll();
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable Long id) throws Exception {
        return userService.findById(id);
    }

    @PatchMapping("/updateUserStatus")
    public User updateUserStatus(@RequestBody User user) throws Exception {
        return userService.updateUserStatus(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}