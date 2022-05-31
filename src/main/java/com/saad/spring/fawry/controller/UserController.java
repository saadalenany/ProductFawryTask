package com.saad.spring.fawry.controller;

import com.saad.spring.fawry.model.User;
import com.saad.spring.fawry.security.CustomUserDetailsService;
import com.saad.spring.fawry.security.JwtConfigurer;
import com.saad.spring.fawry.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtConfigurer jwtTokenConfig;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public UserController() {
    }

    @PostMapping("/user")
    public ResponseEntity<User> post(@RequestBody User model) {
        return ResponseEntity.ok(service.save(model));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(service.list());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        user = service.getByUsername(user.getUsername());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        final String jwt = jwtTokenConfig.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        user = service.save(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        final String jwt = jwtTokenConfig.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
}