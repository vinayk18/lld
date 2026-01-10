package LoggingFramework;

public class ConsoleAppender implements Appender{
    @Override
    public synchronized void append(String message) {
        System.out.println(message);
    }
}
