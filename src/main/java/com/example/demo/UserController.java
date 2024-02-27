package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    List<User> userDetails = new ArrayList<>();

    // READ
    @GetMapping("fetch-all-users")
    public List<User> getAllUsers() {
        return userDetails;
    }

    // Write
    @PostMapping("save-user")
    public User saveUser(@RequestBody User user) {
        userDetails.add(user);
        return user;
    }

    // Update
    @PutMapping("update/user/{id}")
    public List<User> updateUserById(@PathVariable int id, @RequestBody User user) {
        userDetails.forEach(userInfo -> {
            if (userInfo.getId() == id) {
                userInfo.setUsername(user.getUsername());
                userInfo.setLocation(user.getLocation());
            }
        });
        return userDetails;
    }

    // Delete
    @DeleteMapping("delete/user/{id}")
    public int deleteUserById(@PathVariable int id) {
        userDetails = userDetails.stream().filter(user -> user.getId() != id).collect(Collectors.toList());
        return id;
    }

}
