package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserServiceImpl userServiceImpl;
    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        User user = userServiceImpl.findUserByUsername(Username);
        if (user == null) {
            System.out.println("User not found!" + Username);
            throw new UsernameNotFoundException("User " + Username + " was not found in the database");
        }
        System.out.println("Found user!" + Username);

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), getRole(user));
        System.out.println(userDetails);
        return userDetails;
    }

    public Set<SimpleGrantedAuthority> getRole(User user) {
        Role role = user.getRole();
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }
}
