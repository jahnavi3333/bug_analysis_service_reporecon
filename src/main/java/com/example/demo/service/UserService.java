package com.example.demo.service;

import com.example.demo.logs.LogService;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository dao;

    @Autowired
    public LogService logService;

    public String getUserName(int id) {
        try {
            // Simulate runtime exceptions for logging
            Optional<User> userOpt = dao.findById(id);

            if (userOpt.isEmpty()) {
                throw new IllegalArgumentException("Invalid user ID: " + id);
            }

            User user = userOpt.get();

            // Example: simulate ArithmeticException
            int crash = 10 / 0;

            return user.getName().toUpperCase();

        } catch (Exception ex) {
            logService.logError(ex.toString());
            throw ex; // rethrow for ControllerAdvice
        }
    }

    public void saveUser(User user) {
        try {
            dao.save(user);
        } catch (Exception ex) {
            logService.logError(ex.toString());
            throw ex;
        }
    }
}
