package com.saad.spring.fawry.service.impl;

import com.saad.spring.fawry.model.User;
import com.saad.spring.fawry.repository.UserRepository;
import com.saad.spring.fawry.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User get(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("No User found with this ID %s", id)));
    }
}
