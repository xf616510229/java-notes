package multicast;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 单播，点对点通信
 */
public class Unicast {

    public static class UnicastServer {
        public static void main(String[] args) throws Exception {
            ServerSocket server = new ServerSocket(8888);
            while (true) {
                final Socket socket = server.accept();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                            String msg = reader.readLine();
                            if (msg == null) {
                                return;
                            }
                            System.out.println("服务端：" + msg);
                            // 收到请求后，相应给客户端
                            writer.println("你好，小伙子！");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }
    }

    public static class UnicastClient {
        public static void main(String[] args) throws Exception {
            Socket socket = new Socket("localhost", 8888);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println("你好老先生");
            while (true) {
                String data = reader.readLine();
                if (data == null) {
                    break;
                } else {
                    System.out.println("客户端收到数据：" + data);
                }
            }
            reader.close();
            writer.close();
        }
    }

}
