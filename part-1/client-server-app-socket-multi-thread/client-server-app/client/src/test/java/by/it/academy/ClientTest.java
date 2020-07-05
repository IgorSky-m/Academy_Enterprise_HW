package by.it.academy;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ClientTest {
    private Client client1;
    private Client client2;
    private Client client3;

    @Before
    public void prepareRes() throws IOException {
        client1= new Client();
        client2= new Client();
        client3= new Client();
        client1.connectToServer("127.0.0.1",8080);
        client2.connectToServer("127.0.0.1",8080);
        client3.connectToServer("127.0.0.1",8080);
    }

    @After
    public void closeRes() throws IOException {
        client1.closeRes();
        client2.closeRes();
        client3.closeRes();
    }


    @Test
    public void clientsSendsCorrectResponses_MultiThreads() throws IOException {
        client1.sendMsg("hello");
        String msg1 = client1.read();
        client2.sendMsg("okay");
        String msg2 = client2.read();
        client3.sendMsg("test");
        String msg3 =client3.read();
        client1.sendMsg("status");
        String msg4 = client1.read();
        client2.sendMsg("help");
        String msg5 = client2.read();

        Assert.assertEquals("Hello client",msg1);
        Assert.assertEquals("unknown command",msg2);
        Assert.assertEquals("unknown command",msg3);
        Assert.assertEquals("server status: ok",msg4);
        Assert.assertEquals("available commands: 'help', 'status', 'exit'",msg5);


    }

    @Test
    public void sendNullToServer() throws IOException {
        client1.sendMsg(null);
        String msg1 = client1.read();
        Assert.assertEquals("unknown command",msg1);
    }


    @Test
    public void clientlogOut() throws IOException {
        client2.sendMsg("exit");
        String msg1 = client2.read();
        client1.sendMsg("exit");
        String msg2 = client1.read();
        client3.sendMsg("exit");
        String msg3 = client3.read();

        Assert.assertEquals("logout",msg1);
        Assert.assertEquals("logout",msg2);
        Assert.assertEquals("logout",msg3);
    }



}
