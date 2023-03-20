package org.example.cinema.controller;

import org.example.cinema.model.GenericResponse;
import org.example.cinema.model.User;
import org.example.cinema.service.UserManagerService;
import org.example.cinema.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserManagerService userManagerService;

    public UserController(UserService userService, UserManagerService userManagerService) {
        this.userService = userService;
        this.userManagerService = userManagerService;
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public User insert(@RequestBody User user) {
        User u = userService.insert(user.getUsername(), user.getPassword(), user.getRole());
        if (!userManagerService.userExist(u.getUsername())) {
            userManagerService.addSecurityUsers(u.getUsername(), u.getPassword(), u.getRole());
        }
        return u;
    }

    @PutMapping
    public GenericResponse<User> update(@RequestBody User user) {
        return userService.update(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable int id) {
        return userService.deleteById(id);
    }

    @DeleteMapping
    public Boolean deleteAll() {
        return userService.deleteAll();
    }

}
