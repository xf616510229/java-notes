package pojo;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

import java.io.Serializable;

public class Person implements Serializable {
    
    @Protobuf(fieldType = FieldType.STRING)
    private String name;

    @Protobuf(fieldType = FieldType.INT32)
    private Integer age;
    //private transient String pwd; // transient 属性不会被序列化
    //private static String msg = "人"; // 静态资源不会被序列化

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Person getInstance() {
        Person p = new Person();
        p.setAge(100);
        p.setName("张三");
        return p;
    }
    
    public static Person[] getPersons(Integer testSize) {
        Person[] persons = new Person[testSize];
        for (int i = 0; i < testSize; i++) {
            Person p = new Person();
            p.setName("张三");
            p.setAge(10);
            persons[i] = p;
        }
        return persons;
    }
}
