package com.example.demo.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class ErrorLogAppender extends AppenderBase<ILoggingEvent> {

    private final File logFile = new File("sample_logs.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (eventObject.getLevel().isGreaterOrEqual(Level.ERROR)) {
            try (FileWriter fw = new FileWriter(logFile, true)) {
                fw.write(objectMapper.writeValueAsString(Map.of(
                        "timestamp", eventObject.getTimeStamp(),
                        "level", eventObject.getLevel().toString(),
                        "logger", eventObject.getLoggerName(),
                        "message", eventObject.getFormattedMessage()
                )));
                fw.write(System.lineSeparator());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
