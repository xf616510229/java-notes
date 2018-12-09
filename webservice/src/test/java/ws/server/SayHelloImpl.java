package ws.server;

import javax.jws.WebService;

@WebService
public class SayHelloImpl implements ISayHello {
    
    @Override
    public String sayHello(String name) {
        return "你好，" + name;
    }
}
