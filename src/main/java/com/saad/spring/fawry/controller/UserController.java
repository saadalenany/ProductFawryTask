package com.saad.spring.fawry.controller;

import com.saad.spring.fawry.model.User;
import com.saad.spring.fawry.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> post(@RequestBody User model) {
        return ResponseEntity.ok(service.save(model));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

}
