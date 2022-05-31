package com.saad.spring.fawry.security;

import com.saad.spring.fawry.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.saad.spring.fawry.model.User byUsername = userService.getByUsername(s);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(String.valueOf(byUsername.isAdmin())));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return new User(byUsername.getUsername(), byUsername.getPassword(), true, true, true, true, grantedAuthorities);
    }
}
