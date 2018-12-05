package test;

import com.alibaba.fastjson.JSON;
import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caucho.hessian.io.HessianOutput;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojo.Person;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class PerformanceTest {

    public PerformanceTestUtil<Person> getPerformanceTestUtilInstance() {
        return new PerformanceTestUtil<>(Person.getPersons(100000));
    }
    
    @Test
    public void testPer() throws Exception {
        PerformanceTest test = new PerformanceTest();
        System.out.println("------Serialize------");
        test.testSerialize();
        System.out.println("------fastjson------");
        test.testFastJson();
        System.out.println("------jackson------");
        test.testJackson();
        System.out.println("------Protobuf------");
        test.testProtobuf();
        System.out.println("------Hessian------");
        test.testHessian();
    }

    @Test
    public void testFastJson() throws Exception {
        getPerformanceTestUtilInstance().execute(new PerformanceTestUtil.Target<Person>() {
            @Override
            public int doTask(Person data) throws Exception {
                return JSON.toJSONBytes(data).length;
            }
        });
    }

    @Test
    public void testSerialize() throws Exception {
        getPerformanceTestUtilInstance().execute(new PerformanceTestUtil.Target<Person>() {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(buffer);

            @Override
            public int doTask(Person data) throws Exception {
                oos.writeObject(data);
                return buffer.toByteArray().length;
            }
        });
    }

    @Test
    public void testJackson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        getPerformanceTestUtilInstance().execute(new PerformanceTestUtil.Target<Person>() {
            @Override
            public int doTask(Person data) throws Exception {
                return mapper.writeValueAsBytes(data).length;
            }
        });
    }

    @Test
    public void testProtobuf() throws Exception {

        /*
         * 这里使用的是baidu提供的封装包，原本的Google的protobuf需要安装软件，使用较为麻烦
         * 优势，字节数小
         */

        Codec<Person> personCodec = ProtobufProxy.create(Person.class, false);
        getPerformanceTestUtilInstance().execute(new PerformanceTestUtil.Target<Person>() {
            @Override
            public int doTask(Person data) throws Exception {
                return personCodec.encode(data).length;
            }
        });
    }
    
    @Test
    public void testHessian() throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        
        getPerformanceTestUtilInstance().execute(new PerformanceTestUtil.Target<Person>() {
            @Override
            public int doTask(Person data) throws Exception {
                ho.writeObject(data);
                return os.size();
            }
        });
    }

}
