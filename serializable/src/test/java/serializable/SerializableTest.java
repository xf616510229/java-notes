package serializable;

import org.junit.Test;
import pojo.Person;
import pojo.Son;
import test.PerformanceTest;

import java.io.*;

public class SerializableTest extends PerformanceTest {

    @Test
    public void testSerialize() throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person"));
        oos.writeObject(Person.getInstance());
    }

    @Test
    public void testDeSerialize() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person"));
        Person p = (Person) ois.readObject();
        System.out.println(p.getName());
    }

    /**
     * 连续两次序列化同一个对象至同一文件
     * 会发现
     */
    @Test
    public void testTwiceSerialize() throws Exception {
        Person person = Person.getInstance();

        File personFile = new File("person");

        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(personFile));
        oos1.writeObject(person);
        oos1.flush();
        System.out.println("第一次写入对象的");

        oos1.writeObject(person);
        oos1.flush();

        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream("person2"));
        oos2.writeObject(person);

        // 进行字节流比对
        File person1File = new File("person1");
        File person2File = new File("person2");
        // 两次同时写入，不会覆盖，也不会完全追加，而是会在原先的基础上增加一个引用地址；原因：增加效率

        System.out.println("person1: " + person1File.length());
        System.out.println("person2: " + person2File.length());
    }

    /*
     * 序列化可以实现深度克隆
     * 不演示
     */

    /*
     * 如果子类实现了序列化，父类没有实现，则父类中的属性没有办法进行序列化 
     */
    @Test
    public void testFatherAndSonSerialize () throws Exception {
        Son son = new Son();
        son.setMoney("1000亿");
        son.setAge(25);
        son.setName("王校长");

        File sonFile = new File("son");
        
        // 序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(sonFile));
        oos.writeObject(son);
        oos.close();
        
        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(sonFile));
        Son newSon = (Son) ois.readObject();
        System.out.println(newSon.getMoney());
    }
    
}
