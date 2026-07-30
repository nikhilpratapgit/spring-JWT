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

    public User create(User user) {
        return repository.save(user);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public User update(UUID id, User user) {

        User existing = repository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());

        return repository.save(existing);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}