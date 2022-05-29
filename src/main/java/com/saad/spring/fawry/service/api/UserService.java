package com.saad.spring.fawry.service.api;

import com.saad.spring.fawry.model.User;

public interface UserService {

    User save(User user);

    User get(String id);
}
