package jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


//声明根元素为这个实体类，并且再xml中的标签名为students
@XmlRootElement(name="students")
public class Students extends ArrayList<Student> {

	private static final long serialVersionUID = -208654469735538852L;

	// 声明根元素下有多个子元素student
	@XmlElement(name="student") // @XmlElement将Java对象的属性映射为xml的节点
	public ArrayList<Student> getStudents(){
		return this;
	}

}
