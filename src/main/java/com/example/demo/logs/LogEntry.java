package com.example.demo.logs;

import lombok.Data;

@Data
public class LogEntry {
    private long timestamp;
    private String message;
}
