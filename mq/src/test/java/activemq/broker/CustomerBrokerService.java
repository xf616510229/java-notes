package activemq.broker;

import org.apache.activemq.broker.BrokerService;

/**
 * 基于TCP协议的最简单的Broker
 * @author Feahters
 * @version 1.0
 * @date 2019/3/25
 */
public class CustomerBrokerService {

    public static void main(String[] args) {

        BrokerService brokerService = new BrokerService();
        try {
            brokerService.setUseJmx(true); // 使用使用JMX（Java Management Extensions，即Java管理扩展）
            brokerService.addConnector("localhost:61617"); // 绑定地址与端口
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
