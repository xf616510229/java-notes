package jms.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * AppConsumer
 * jms消息消费者 point-to-point 队列模型
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 20:36
 */
public class AppConsumer {
    /*连接地址，默认端口61616， activemq5版本以上，url 应该填写tcp://localhost:61616?jms.useAsyncSend=true*/
    private static final String URL = "tcp://127.0.0.1:61616";
    private static final String QUEUE_NAME = "queue-first";

    public static void main(String[] args) {
        //1. 创建连接工厂
        ConnectionFactory cf = new ActiveMQConnectionFactory(URL);
        //2. 使用工厂创建连接对象
        Connection c = null;
        try {
            c = cf.createConnection();

            //3. 启动连接
            c.start();
            //4. 创建会话
            Session session = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //5. 创建一个目标（消息发布和接收的地点，包含队列和主题）,这里是队列
            Destination destination = session.createQueue(QUEUE_NAME);
            //6. 创建一个消费者
            MessageConsumer consumer = session.createConsumer(destination);
            //7. 同步消费
            for (int i = 0; i < 100; i++) {
                System.out.println(consumer.receive());
            }
            // 关闭连接，异步状态下，需要在程序退出时关闭，关闭过快，则有可能接收不到，应用程序就停止了
            consumer.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null)
                    c.close(); // 关闭连接，否则一直会阻塞
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
