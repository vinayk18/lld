package LoggingFramework;

import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements Appender {

    private final String filePath;

    public FileAppender(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public synchronized void append(String message) {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(message + "\n");
        } catch (IOException e) {
            // logging must never crash the app
        }
    }
}