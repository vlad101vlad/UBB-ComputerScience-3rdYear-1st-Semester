package en.ubbcluj.info.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Class used for logging diverse messages of the results of this application
 */
public class LoggerService {
    private FileWriter logFileWriter;

    public LoggerService() throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String loggingTimestamp = simpleDateFormat.format(new Timestamp(System.currentTimeMillis())) + ".txt";

        String PATH_FROM_ROOT = "src/en/ubbcluj/info/logs/";
        File loggingFile = new File(PATH_FROM_ROOT + loggingTimestamp);
        this.logFileWriter = new FileWriter(loggingFile);
    }

    public void createStatistics() throws IOException {
        String PATH_FROM_ROOT = "src/en/ubbcluj/info/logs/";
        File loggingFile = new File(PATH_FROM_ROOT + "statistics.txt");
        this.logFileWriter = new FileWriter(loggingFile);
    }

    public void log(String message) throws IOException {
        logFileWriter.append(message);
    }

    public void closeLogWriter() throws IOException {
        logFileWriter.close();
    }

    /**
     * Prints general information about the machine this code was run
     * @throws IOException
     */
    public void logInfo() throws IOException {
        String message = "Intel(R) Core(TM) i7-6820HQ\n" +
            "CPU @ 2.70GHz   2.71 GHz\n" +
            "64 GB RAM\n\n\n" +
            "Programming language: Java\n";
        this.log(message);
    }

}
