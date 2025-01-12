package com.cmdotenter.VetCare.controller;

import java.util.List;

import com.cmdotenter.VetCare.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cmdotenter.VetCare.dto.request.BaseUserRequest;
import com.cmdotenter.VetCare.entity.User;
import com.cmdotenter.VetCare.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll();
        log.info("Find all users");
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody BaseUserRequest request){
        userService.save(request);
        log.info("User saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        log.info("User deleted");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody BaseUserRequest request){
        userService.update(id, request);
        log.info("User updated");
        return ResponseEntity.ok().build();
    }
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request){
        User user = userService.login(request);
        log.info("User logged in");
        return ResponseEntity.ok(user);
    }

}
