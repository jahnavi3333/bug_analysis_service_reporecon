package com.example.demo.logs;

import com.example.demo.logs.LogEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);
    private static final String LOG_FILE = "sample_logs.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public void logError(String message) {
        // Write to console / logger at ERROR level
        logger.error(message);

        // Write to JSON file
        LogEntry entry = new LogEntry();
        entry.setTimestamp(System.currentTimeMillis());
        entry.setMessage(message);

        try {
            File file = new File(LOG_FILE);
            List<LogEntry> logs;

            if (file.exists()) {
                logs = new ArrayList<>(Arrays.asList(mapper.readValue(file, LogEntry[].class)));
            } else {
                logs = new ArrayList<>();
            }

            logs.add(entry);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, logs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
