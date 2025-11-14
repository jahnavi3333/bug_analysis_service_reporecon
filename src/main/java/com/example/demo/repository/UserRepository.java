package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    // ❌ Intentional bug: this map never gets populated
    private Map<Integer, User> users = new HashMap<>();

    public User findUserById(int id) {
        return users.get(id); // Always returns NULL
    }

    public void saveUser(User user) {
        users.put(user.getId(), user);
    }
}
