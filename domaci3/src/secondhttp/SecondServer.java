package secondhttp;

import http.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SecondServer {
    public static final int TCP_PORT = 8081;

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(TCP_PORT);
            while (true) {
                Socket sock = ss.accept();
                new Thread(new SecondServerThread(sock)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
