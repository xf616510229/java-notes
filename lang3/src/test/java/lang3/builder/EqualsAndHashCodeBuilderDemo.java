package lang3.builder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.Test;

/**
 * EqualsBuilderDemo
 * Equals构建
 * <p>
 *
 * @author Feathers
 * @date 2018-06-07 13:49
 */
class EqualsAndHashCodeBuilderDemo {

    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setId(1);
        p1.setName("李四");

        Person p2 = new Person();
        p2.setId(1);
        p2.setName("张三");

        System.out.println(p1.equals(p2));
    }
}

class Person {
    private Integer id;
    private String name;
    private String age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return new EqualsBuilder()
                .append(id, person.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}


