package ws;

/**
 * 调用webservice接口方式一：使用wsimport 命令生成调用代码
 * wsimport -keep http://localhost:8888/ws/hello?wsdl  
 * -keep 表示保存源码，如果不加入此参数，java代码会被删除，只存在class文件
 */
public class Client {

    public static void main(String[] args) {
        SayHelloImplService sayHelloImplService = new SayHelloImplService();
        SayHelloImpl sayHello = sayHelloImplService.getSayHelloImplPort();
        String msg = sayHello.sayHello("李四");
        System.out.println(msg);
    }
    
}
