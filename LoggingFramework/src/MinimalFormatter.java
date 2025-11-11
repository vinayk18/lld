public class MinimalFormatter implements LogFormatter {
    @Override
    public String format(String loggerName, LogLevel level, String message) {
        return "[" + level + "] " + message;
    }
}

