package jms.transaction;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 事务性会话，生产者
 * <p>
 *
 * @author Feathers
 * @date 2018-05-27 20:11
 */
public class TransactionProducer {

    /*连接地址，默认端口61616， activemq5版本以上，url 应该填写tcp://localhost:61616?jms.useAsyncSend=true*/
    private static final String URL = "tcp://127.0.0.1:61616";
    private static final String QUEUE_NAME = "queue-first";

    public static void main(String[] args) {
        ConnectionFactory cf = new ActiveMQConnectionFactory(URL);
        Connection c = null;
        try {
            c = cf.createConnection();
            c.start();
            Session session = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(destination);

            for (int i = 0; i < 100; i++) {
                TextMessage tx = session.createTextMessage("我是消息 " + i);
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
                    c.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
