package jms.p2p;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * AppProducer
 * jms 消息生产者 point-to-point 队列模型
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 20:11
 */
public class AppProducer {

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
            //5. 创建一个目标（消息发布和接收的地点，包含队列和主题）,此处是队列
            Destination destination = session.createQueue(QUEUE_NAME);
            //6. 创建一个生产者
            MessageProducer producer = session.createProducer(destination);

            for (int i = 0; i < 100; i++) {
                // 准备消息
                TextMessage tx = session.createTextMessage("我是消息 " + i);
                // 发送消息
                producer.send(tx);
                System.out.println("发送消息" + tx.getText());
            }
            producer.close();
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
