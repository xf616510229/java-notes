package jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * AppConsumer
 * jms消息消费者 队列模型
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 20:36
 */
public class AppConsumer {
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
        //6. 创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //7. 创建一个监听器，用于监听消息队列发送的消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收到消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        // 关闭连接，需要在程序退出时关闭，关闭过快，则有可能接收不到，应用程序就停止了
        //consumer.close();
    }
}
