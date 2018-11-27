package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.util.Date;

//控制字段或属性的序列化。FIELD表示JAXB将自动绑定Java类中的每个非静态的（static）、非瞬态的（由@XmlTransient标注）字段到XML。
//其他值还有XmlAccessType.PROPERTY和XmlAccessType.NONE。
@XmlAccessorType(XmlAccessType.FIELD)
public class Student implements Serializable {

	private static final long serialVersionUID = -4907165887085952183L;

	//将Java类的一个属性映射到与属性同名的一个XML属性。
	@XmlAttribute(name="id")
	private Long id;
	
	private String name;
	
	private Date birthday;
	
//	@XmlElement(name = "address") // 如果属性名和xml元素名不一致，使用该方式
	private Address address;
	
	public Student() {
	}

	public Student(Long id, String name, Date birthday, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", adress=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}
}
