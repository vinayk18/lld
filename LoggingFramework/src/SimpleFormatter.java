import java.time.LocalDateTime;

public class SimpleFormatter implements LogFormatter {
    @Override
    public String format(String loggerName, LogLevel level, String message) {
        String time = LocalDateTime.now().toString();
        String thread = Thread.currentThread().getName();
        return time + " [" + level + "] (" + thread + ") " + loggerName + " - " + message;
    }
}
