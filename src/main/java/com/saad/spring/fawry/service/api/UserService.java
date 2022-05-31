package com.saad.spring.fawry.service.api;

import com.saad.spring.fawry.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User get(String id);

    List<User> list();

    User getByUsername(String username);
}
