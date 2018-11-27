package lang3.builder;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ToStringBuilderDemo
 * <p>
 *
 * @author Feathers
 * @date 2018-06-07 14:14
 */
class ToStringBuilderDemo {

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setId(1);
        dog.setName("旺财");
        dog.setAge("1");

        System.out.println(dog);
    }

}

class Dog {
    private int id;
    private String name;
    private String age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("age", age)
                .toString();
    }
}
