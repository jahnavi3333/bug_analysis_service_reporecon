package com.example.demo.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final File logFile = new File("sample_logs.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    // This will catch all exceptions thrown in Controllers/Services/Repositories
    @ExceptionHandler(Exception.class)
    public void handleAllExceptions(Exception ex) {
        Map<String, Object> logEntry = new HashMap<>();
        logEntry.put("timestamp", LocalDateTime.now().toString());
        logEntry.put("exception", ex.getClass().getName());
        logEntry.put("message", ex.getMessage());
        logEntry.put("stackTrace", ex.getStackTrace());

        try (FileWriter fw = new FileWriter(logFile, true)) {
            fw.write(objectMapper.writeValueAsString(logEntry));
            fw.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
