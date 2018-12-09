package ws;

import javax.xml.ws.Endpoint;

/**
 * 调用请查看webservice-client项目
 */
public class Bootstrap {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/ws/hello", new SayHelloImpl());
        System.out.println("publish success");
        // 访问http://localhost:8888/ws/hello?wsdl 将会出现wsdl文件
    }
    
}
