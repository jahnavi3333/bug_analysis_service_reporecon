package com.example.demo.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_FILE = "sample_logs.json";
    private ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        System.out.println("Caught exception: " + ex.getMessage());

        Map<String, Object> logEvent = new HashMap<>();
        logEvent.put("timestamp", System.currentTimeMillis());
        logEvent.put("message", ex.toString());

        try {
            File file = new File(LOG_FILE);
            Map[] existing = file.exists() ? mapper.readValue(file, Map[].class) : new Map[0];
            Map[] updated = new Map[existing.length + 1];
            System.arraycopy(existing, 0, updated, 0, existing.length);
            updated[existing.length] = logEvent;
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, updated);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error occurred: " + ex.getMessage();
    }
}
