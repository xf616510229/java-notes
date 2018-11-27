package jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * AppProducer
 * jms 消息生产者 队列模型
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 20:11
 */
public class AppProducer {

    /*连接地址，默认端口61616， activemq5版本以上，url 应该填写tcp://localhost:61616?jms.useAsyncSend=true*/
    //private static final String URL = "tcp://192.168.0.14:61616";
    private static final String URL = "tcp://127.0.0.1:61616";
    private static final String QUEUE_NAME = "queue-test";

    public static void main(String[] args) throws JMSException {
        //1. 创建连接工厂
        ConnectionFactory cf = new ActiveMQConnectionFactory(URL);
        //2. 使用工厂创建连接对象
        Connection c = cf.createConnection();
        //3. 启动连接
        c.start();
        //4. 创建会话 参1：是否使用事务
        Session session = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5. 创建一个目标（消息发布和接收的地点，包含队列和主题）
        Destination destination = session.createQueue(QUEUE_NAME);
        //6. 创建一个生产者
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            // 准备消息
            TextMessage tx = session.createTextMessage("我是消息 " + i);
            // 发送消息
            producer.send(tx);
            System.out.println("发送希消息" + tx.getText());
        }
        //关闭连接
        producer.close();

        // 发送完毕后，会在http://127.0.0.1:8161/admin/queues.jsp 中看到我们的消息队列的100条消息
    }
}
