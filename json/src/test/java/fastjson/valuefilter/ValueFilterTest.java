package fastjson.valuefilter;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class ValueFilterTest {

    @Test
    public void testDesensitization() {
        Person p = new Person();
        p.setId("123");
        p.setName("张三");
        p.setPhone("13861507666");
        String s = JSON.toJSONString(p, new DesensitizationValueFilter());
        System.out.println(s);
    }
}
