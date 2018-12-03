package serversocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            final Socket socket = server.accept();
            new Thread() {
                @Override
                public void run() {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                        while (true) {
                            //读取客户端发送过来的消息
                            String line = reader.readLine(); 
                            if (line == null) {
                                break;
                            } else {
                                System.out.println(new Date().getTime() +  "服务端收到数据：" + line);
                            }
                            //给客户端发送一条消息回复
                            writer.println(" - 我收到了你的消息，客户端");
                            writer.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
    
}
