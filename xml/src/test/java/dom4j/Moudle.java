package dom4j;

import java.util.Date;

public class Moudle {
	
	private Long id;
	
	private String name;
	
	private Integer age;
	
	private Date birthday;

	public Moudle() {

	}

	public Moudle(Long id, String name, Integer age, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Moudle [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append("]");
		return builder.toString();
	}

}
