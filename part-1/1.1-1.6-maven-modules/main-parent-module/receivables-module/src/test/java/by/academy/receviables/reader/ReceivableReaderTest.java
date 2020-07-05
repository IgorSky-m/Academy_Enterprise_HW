package by.academy.receviables.reader;



import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReceivableReaderTest {
    @Test
    public void whenCreateManyReaders () {
        ReceivablesReader reader = ReceivablesReader.getReader();
        ReceivablesReader reader1 = ReceivablesReader.getReader();
        assertEquals(reader,reader1);

    }

    @Test
    public void whenInsertIncorrectFilePath(){
        ReceivablesReader reader = ReceivablesReader.getReader();
        List<String> testList = new ArrayList<>();
        List<String> testList1 = reader.read("Insert Incorrect Path");
        assertEquals(testList,testList1);
    }



}
