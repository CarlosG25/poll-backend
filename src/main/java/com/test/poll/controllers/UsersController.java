package com.test.poll.controllers;

import com.test.poll.domains.User;
import com.test.poll.exceptions.UserNotFoundException;
import com.test.poll.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User save(@RequestBody User user) {
        return this.userService.save(user);
    }

    @GetMapping("{id}")
    public User findById(@PathVariable String id) throws UserNotFoundException {
        return this.userService.findById(id);
    }

    @GetMapping
    public User findAll()  {
        return (User) this.userService.findAll();
    }
}

