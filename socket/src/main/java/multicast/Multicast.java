package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * 组播
 * 如果你在大街上喊一声美女，将会有一组人(女)回头看你
 */
public class Multicast {

    public static class MulticastServer {
        public static void main(String[] args) {
            try {
                //  组定义
                //  组的地址端处于 224.0.0.0 - 239.255.255.255
                InetAddress group = InetAddress.getByName("224.5.5.6");
                MulticastSocket server = new MulticastSocket();
                for (int i = 0; i < 10; i++) {
                    String data = "你好年轻人";
                    server.send(new DatagramPacket(data.getBytes(), data.getBytes().length, group, 8888)); // DatagramPacket: UDP数据包
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MulticastClient {
        public static void main(String[] args) {
            try {
                InetAddress group = InetAddress.getByName("224.5.5.6");
                MulticastSocket client = new MulticastSocket(8888);
                client.joinGroup(group); // 加入指定的组中
                byte[] buf = new byte[256];
                while (true) {
                    DatagramPacket msgPkg = new DatagramPacket(buf, buf.length);
                    client.receive(msgPkg);
                    
                    String msg = new String(msgPkg.getData());
                    System.out.println(msg);
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
