package xmemcached;

import net.rubyeye.xmemcached.MemcachedClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMemcachedClient {

    @Test
    public void test() throws Exception{
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext-xmemcached");
        MemcachedClient memcachedClient = (MemcachedClient) app.getBean("memcachedClient");
        memcachedClient.set("xmemcached", 0, "haha");
    }

}
