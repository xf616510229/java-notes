package beanutils;


import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeanUtilsDemo {

    class Person {
        private String name;
        private String age;

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }
    }

    @Test
    private void test(){

        List<Person> personList = new ArrayList<>();
        Collection names = CollectionUtils.collect(personList, new BeanToPropertyValueTransformer("name"));
        System.out.println(names);

        String s = " ";
    }

}
