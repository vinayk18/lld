package LoggingFramework;

import java.time.format.DateTimeFormatter;

public class SimpleFormatter implements LogFormatter{

    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String format(LogEvent e) {
        return String.format(
                "%s [%s] [%s] %s - %s",
                e.getTimeStamp().format(DF),
                e.getLevel(),
                e.getThreadName(),
                e.getLoggerName(),
                e.getMessage()
        );
    }
}
