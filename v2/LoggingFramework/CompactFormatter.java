package LoggingFramework;

public class CompactFormatter implements LogFormatter {
    @Override
    public String format(LogEvent e) {
        return "[" + e.getLevel() + "] " + e.getMessage();
    }
}