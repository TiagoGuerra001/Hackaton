package com.example.demo.config;

import com.example.demo.entity.UserDAO;
import com.example.demo.repository.UserDAORepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Service;

@Service
public class UserDetailsConfig implements UserDetailsService {

    @Autowired
    private UserDAORepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDAO> user = userRepository.findByUsername(username);
        if (user.isPresent()) {

            return new UserDataModel(user.get());

        }
        throw new UsernameNotFoundException("User not found");
    }

}
