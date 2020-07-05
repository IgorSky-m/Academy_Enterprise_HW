package by.it.academy;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Logger logger;

    public Client(){
        logger =Logger.getLogger("echo client log");

    }

    public void connectToServer (String ip, int port) throws IOException {
        clientSocket = new Socket(ip,port);
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new PrintWriter(clientSocket.getOutputStream(),true);

    }

    public void sendMsg (String msg) throws IOException {
        writer.println(msg);
    }

    public String read() throws IOException {
            return reader.readLine();
        }



    public void closeRes() throws IOException {
        reader.close();
        writer.close();
        clientSocket.close();

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();
        try {
            client.connectToServer("127.0.0.1", 8081);
            System.out.println("connected to Server");
            String responseLine;
            while (true) {
                System.out.print("enter: ");
                client.sendMsg(scanner.nextLine());
                responseLine = client.read();
                System.out.println(responseLine);
                if (responseLine.equalsIgnoreCase("logout")) {
                    break;
                }

            }
            client.closeRes();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}