package com.example.demo;

import com.example.demo.logs.LogService;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class StartupErrorSimulator implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logservice;

    @Override
    public void run(String... args) throws Exception {
        // 1️⃣ Non-existent user -> IllegalArgumentException
        try {
            userService.getUserName(999); // user ID 999 doesn't exist
        } catch (Exception ignored) {}

        // 2️⃣ ArithmeticException -> divide by zero
        try {
            int crash = 10 / 0;
        } catch (Exception ex) {
            userService.logService.logError(ex.toString());
        }

        // 3️⃣ NullPointerException
        try {
            User user = null;
            user.getName(); // will throw NullPointerException
        } catch (Exception ex) {
            userService.logService.logError(ex.toString());
        }

        // 4️⃣ ArrayIndexOutOfBoundsException
        try {
            int[] arr = new int[2];
            int x = arr[5]; // invalid index
        } catch (Exception ex) {
            userService.logService.logError(ex.toString());
        }
    }
}
