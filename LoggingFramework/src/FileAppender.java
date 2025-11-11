import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements Appender{

    private final String filePath;

    public FileAppender( String filePath){
        this.filePath = filePath;
    }

    @Override
    public void append(String message) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.err.println("FileAppender failed: " + e.getMessage());
        }
    }
}
