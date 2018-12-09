package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService // 定义一个webservice接口
public interface ISayHello {
    
    @WebMethod // 声明一个方法
    String sayHello(String name);

}
