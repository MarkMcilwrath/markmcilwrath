package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.service.UserService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.save(user.getFirstname(), user.getLastname(), user.getEmail(), user.isAdmin()));
    }

    @PutMapping("/add")
    public ResponseEntity<User> editUser(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.save(user.getFirstname(), user.getLastname(), user.getEmail(), user.isAdmin()));
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) throws NotFoundException {
        return ResponseEntity.ok().body(userService.getUser(userId));
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String userEmail) throws NotFoundException {
        return ResponseEntity.ok().body(userService.getUserByEmail(userEmail));
    }

    @GetMapping()
    public ResponseEntity<Set<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }


}
