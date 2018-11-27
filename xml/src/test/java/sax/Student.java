package sax;

import java.util.Set;

/**
 * 学生信息表
 * 
 * @author jiqinlin
 * 
 */
public class Student {
    /** 姓名 * */
    private String name;
    /** 性别 * */
    private String sex;
    /** 所学课程 * */
    private Set<Lesson> lessons;

    public Student() {
    }

    public Student(String name, String sex, Set<Lesson> lessons) {
        this.name = name;
        this.sex = sex;
        this.lessons = lessons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }
    
    

    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [name=");
		builder.append(name);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", lessons=");
		builder.append(lessons);
		builder.append("]");
		return builder.toString();
	}

	public void setSex(String sex) {
        this.sex = sex;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

}