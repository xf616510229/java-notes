package serversocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class SocketClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8888);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //读取服务端信息
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);  // 往服务端写数据
        writer.println("你好，服务端");
        while (true) {
            String serverData = reader.readLine();
            if (serverData == null) {
                break;
            } else {
                System.out.println(new Date().getTime() + " - 客户端收到数据：" + serverData);
            }
        }
        writer.close();
        socket.close();
    }

}
