package LoggingFramework;

import java.time.LocalDateTime;

public class LogEvent {

    private final LocalDateTime timeStamp;
    private final LogLevel level;
    private final String loggerName;
    private final String message;
    private final String threadName;

    public LogEvent(LogLevel level, String loggerName, String message) {
        this.timeStamp = LocalDateTime.now();
        this.level = level;
        this.loggerName = loggerName;
        this.message = message;
        this.threadName = Thread.currentThread().getName();
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getMessage() {
        return message;
    }

    public String getThreadName() {
        return threadName;
    }

}
