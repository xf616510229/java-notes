package serversocket;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket socket = server.accept();
            
        }
    }
    
}
