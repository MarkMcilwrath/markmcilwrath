package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.service.UserService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.save(user.getFirstname(), user.getLastname(), user.getEmail(), user.isAdmin())
        );
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) throws NotFoundException {
        return ResponseEntity.ok().body(userService.getUser(userId));
    }

    @GetMapping()
    public ResponseEntity<Set<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }


}
