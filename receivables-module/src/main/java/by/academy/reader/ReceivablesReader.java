package by.academy.reader;

import by.academy.interfaces.IReader;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReceivablesReader implements IReader<List<String>> {
    private final Logger logger;
    private static ReceivablesReader reader;

    private ReceivablesReader() {
        this.logger = Logger.getLogger("reader log");
    }

    public static synchronized ReceivablesReader getReader (){
        if (reader == null) reader = new ReceivablesReader();
        return reader;
    }

    @Override
    public List<String> read(String path) {
        long linesCounter = 0;
        String line;
        List<String> stringList = new ArrayList<>();
        try (BufferedReader recReader = new BufferedReader(new FileReader(path))) {
            while (true) {
                line = recReader.readLine();
                if (line == null) break;
                linesCounter++;
                stringList.add(line);
            }
            if (linesCounter == 0) {
                logger.warning("reading file is empty");
            }
        } catch (IOException e) {
            logger.warning("Can't find file. Insert correct file path");
            return stringList;
        }
        return stringList;
    }





}
