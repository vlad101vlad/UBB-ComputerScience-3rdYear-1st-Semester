package en.ubbcluj.info.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class LoggerService {
    private final FileWriter logFileWriter;

    public LoggerService() throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String loggingTimestamp = simpleDateFormat.format(new Timestamp(System.currentTimeMillis())) + ".txt";

        String PATH_FROM_ROOT = "src/en/ubbcluj/info/logs/";
        File loggingFile = new File(PATH_FROM_ROOT + loggingTimestamp);
        this.logFileWriter = new FileWriter(loggingFile);
    }

    public void log(String message) throws IOException {
        logFileWriter.append(message);
    }

    public void closeLogWriter() throws IOException {
        logFileWriter.close();
    }


}
