package by.it.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Server {
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private final int availableProcessors;

    public Server (){
        availableProcessors = Runtime.getRuntime().availableProcessors();
    }

    public void start (int port) throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(availableProcessors);
        while (true) {
            executorService.submit(new ClientHandler(serverSocket.accept()));
        }
    }

    public void stop () throws IOException {
        serverSocket.close();
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private  PrintWriter writer;
        private BufferedReader reader;
        private Logger logger;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            Logger.getLogger("Client Handler log");
        }

        private String createResponse(String request) {
            switch (request.toLowerCase()) {
                case "status": return "server status: ok";
                case "help": return "available commands: 'help', 'status', 'exit'";
                case "exit" :return "exit";
                case "hello": return "Hello client";
                default: return "unknown command";
            }
        }

        public void run () {
            try {
                String clientName = Thread.currentThread().getName();
                System.out.println("client " +clientName+" connected");
                writer = new PrintWriter(clientSocket.getOutputStream(),true);
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String request;

                while ((request = reader.readLine()) != null) {
                    System.out.println("receive request \'"+request+ "\' from client: "+ clientName);
                    if ("exit".equalsIgnoreCase(request)) {
                        writer.println("logout\r\n");
                        break;
                    }
                    String response = createResponse(request);
                    writer.println(response);
                    System.out.println("send personse \'"+response+ "\' to client: "+ clientName);


                }
                System.out.println("client " +clientName+" disconnected");
                reader.close();
                writer.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }



    }

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("log main method");
        Server server = new Server();
        try {
            server.start(8081);
        }catch (IOException e) {
            logger.log(new LogRecord(Level.WARNING,"IOException in method start() class Server"));
        }
    }

}
