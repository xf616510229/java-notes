package jms.transaction;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 事务性会话，消费者
 * @author Feahters
 * @version 1.0
 * @date 2019/3/25
 */
public class TransactionConsumer {
    private static final String URL = "tcp://127.0.0.1:61616";
    private static final String QUEUE_NAME = "queue-first";

    public static void main(String[] args) {
        ConnectionFactory cf = new ActiveMQConnectionFactory(URL);
        Connection c = null;
        try {
            c = cf.createConnection();
            c.start();
            // 事务：事务未提交，不会确认消息，可以使用commit方法确认，rollback方法回滚
            
            // 发送端如果消息未确认，在队列/主题中不会出现该消息
            // 接收端如果消息未确认，在队列中不会移除该消息（主题无效）
            
            // 参数1为false，代表创建一个非事务性会话，此时参2（应答模式）才会生效
            // 非事务性会话共有以下三种应答模式：
            // AUTO_ACKNOWLEDGE，自动确认，recive方法返回以后或者[MessageListener.onMessage] 方法成功返回以后，会话会自动确认消息
            // CLIENT_ACKNOWLEDGE，客户端确认，需要客户端通过消息对象textMessage.acknowledge()方法手动确认消息，确认时将会目前客户端消费的所有消息一起确认
            // DUPS_OK_ACKNOWLEDGE，延迟确认
            Session session = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageConsumer consumer = session.createConsumer(destination);
            for (int i = 0; i < 100; i++) {
                System.out.println(consumer.receive());
            }
            consumer.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (c != null)
                    c.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
    
