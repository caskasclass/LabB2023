package connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import intefaces.RequestElaboration_withClose;

public class Proxy implements RequestElaboration_withClose {
    Socket connection = null;

    public Proxy() {
        try {
            InetAddress addr = InetAddress.getByName(null);
            connection = new Socket();
            connection.connect(new InetSocketAddress(addr, PORT),1000);
        }catch(SocketTimeoutException timeout){
            System.err.println("Connection timeout expired.   ");
            timeout.printStackTrace();
        }catch (UnknownHostException e) {
            System.err.println("Missing or wrong host IP.");
            e.printStackTrace();
        }catch (IOException ioe) {
            System.err.println("Connection to server failed.");
            //ioe.printStackTrace();
        }
    }

    public void getStatus() {
        System.out.println(connection.isConnected() ? "Connection status : running" : "Connection status : refused");
    }

    @Override
    public void close() {
        System.out.println("Closing the connection...");
        try {
            connection.close();
            System.out.println("Connection closed.");
        } catch (IOException e) {
            System.err.println("Cant close connection...");
            e.printStackTrace();
        }

    }
}
