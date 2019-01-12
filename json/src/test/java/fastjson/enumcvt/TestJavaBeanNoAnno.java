package fastjson.enumcvt;

import com.alibaba.fastjson.annotation.JSONField;

public class TestJavaBeanNoAnno {

    private String name;
    private Integer age;
    
    private ResultEnum resultEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }

    @Override
    public String toString() {
        return "TestJavaBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", resultEnum=" + resultEnum +
                '}';
    }
}
