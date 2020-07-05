package servlets;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("files/test.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s;
        while ((s = reader.readLine()) != null) {
            System.out.println(s);
        }

    }
}
