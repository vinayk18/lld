package LoggingFramework;

public interface Appender {
    void append(String formattedMessage);
    default void close() {}
}