package lang3.builder;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * CompareToBuildDemo
 * 比较构建
 * <p>
 *
 * @author Feathers
 * @date 2018-06-07 11:44
 */
class CompareToBuildDemo {

    public static void main(String[] args) {
        Employee boss = new Employee(1001, "王老板", Position.BOSS);

        Employee manager1 = new Employee(1002, "张经理", Position.MANAGER);
        Employee manager2 = new Employee(1004, "赵经理", Position.MANAGER);
        Employee stuff1 = new Employee(1003, "李小二", Position.STUFF);
        Employee stuff2 = new Employee(1005, "王小二", Position.STUFF);

        ArrayList<Employee> employees = new ArrayList<Employee>() {{
            this.add(stuff1);
            this.add(manager2);
            this.add(stuff2);
            this.add(manager1);
            this.add(boss);
        }};

        employees.sort(new PositionCompare());
        System.out.println(manager1.compareTo(manager2));
        System.out.println(employees);
    }
}

class Employee implements Comparable<Employee> {

    // 根据进入公司的先后顺序编码的
    private int id;
    private String name;
    private Position position;

    public Employee(int id, String name, Position position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", position=").append(position);
        sb.append('}');
        return sb.toString();
    }

    // 默认的比较器，按照自立排序
    @Override
    public int compareTo(Employee o) {
        return new CompareToBuilder()
                .append(id, o.id) // 先按照资历排序, 倒叙切换两个参数的位置
                .toComparison();
    }
}

// 定义按照职位的比较器
class PositionCompare implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return new CompareToBuilder()
                .append(o1.getPosition(), o2.getPosition())
                .append(o1.getId(), o2.getId())
                .toComparison();
    }
}

enum Position {
    BOSS, MANAGER, STUFF
}