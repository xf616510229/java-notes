package spymemcached;

import net.spy.memcached.MemcachedClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMemcachedClient {

    @Test
    public void test() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext-spymemcahed.xml");
        MemcachedClient spyMemcachedClient = (MemcachedClient) app.getBean("spyMemcachedClient");

        spyMemcachedClient.set("aaa", 36000, "set/get");
    }

}
