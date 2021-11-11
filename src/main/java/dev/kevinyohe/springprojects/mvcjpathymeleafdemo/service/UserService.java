package dev.kevinyohe.springprojects.mvcjpathymeleafdemo.service;

import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(final UserRepository userRepository){
        this.repository = userRepository;
    }
}
