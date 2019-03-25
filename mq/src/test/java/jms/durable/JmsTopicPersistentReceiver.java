package jms.durable;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Durable Subscribes 主题的持久订阅
 *
 * @author Feahters
 * @version 1.0
 * @date 2019/3/25
 */
public class JmsTopicPersistentReceiver {


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("DUBBO-ORDER"); // 设置客户端id
            connection.start();

            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建队列（如果队列已经存在则不会创建， first-queue是队列名称）
            //destination表示目的地
            Topic topic = session.createTopic("first-topic");
            //创建消息接收者
//            MessageConsumer consumer = session.createConsumer(destination);
            TopicSubscriber consumer = session.createDurableSubscriber(topic, "DUBBO-ORDER");
            TextMessage textMessage = (TextMessage) consumer.receive();
            System.out.println(textMessage.getText());
            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
