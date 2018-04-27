package logger;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEventLogger implements EventLogger {
    private File filename;

    public FileEventLogger(String filename) {
        this.filename = new File(filename);
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(filename, event.toString(), Charset.defaultCharset(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
