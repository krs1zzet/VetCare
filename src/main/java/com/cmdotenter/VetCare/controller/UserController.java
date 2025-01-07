package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BaseUserRequest;
import com.cmdotenter.VetCare.entity.User;
import com.cmdotenter.VetCare.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll(){
        List<User> userDTOList = userService.findAll();
        log.info("Find all users");
        return ResponseEntity.ok(userDTOList);
    }
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody BaseUserRequest request){
        userService.save(request);
        log.info("User saved");
        return  ResponseEntity.ok().build();
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        log.info("User deleted");
        return ResponseEntity.ok().build();
    }

}
