package by.academy;



import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("main log");
        logger.log(new LogRecord(Level.INFO,"start receivables module"));


    }



}
