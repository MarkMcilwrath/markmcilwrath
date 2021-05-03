package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.service.UserService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.save(user.getFirstname(), user.getLastname(), user.getEmail(), user.isAdmin()));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws InvocationTargetException, IllegalAccessException {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.update((user)));
    }


    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{userEmail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String userEmail) throws NotFoundException {
        return ResponseEntity.ok().body(userService.getUserByEmail(userEmail));
    }

    @GetMapping("/id/{userEmail}")
    public ResponseEntity<String> getUserIDByEmail(@PathVariable String userEmail) throws NotFoundException
    {
        return ResponseEntity.ok().body(userService.getUserByEmail(userEmail).getUuid());
    }
    //For the purpose of the functional prototype and demonstration
    // in a more developed soultion this would be replaced by Login functionality


    @GetMapping()
    public ResponseEntity<Set<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }


}
