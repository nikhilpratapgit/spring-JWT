package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.getAllUsers();
    }

    public User getById(UUID id) {
        return repository.findUserById(id).orElse(null);
    }

    public int update(UUID id, User user) {
        return repository.UpdateUserName(id,user.getName());
    }

    public void delete(UUID id) {
        repository.deleteUserById(id);
    }
}