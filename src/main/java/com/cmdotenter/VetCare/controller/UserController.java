package com.cmdotenter.VetCare.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
