package jaxb;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

// 使用Jaxb解析xml文件studentslist.xml                   反序列化！！！    Unmarshaller          序列化 Marshaller
// 具体操作再JDK1.7 javax.xml.bin.Jaxb类下
public class JaxbParser {
	public static void main(String[] args) {
		
		try {
			Students students = (Students) JaxbUtil.read("src/me/feathers/jaxb/studentlist.xml", Students.class);
			
			for (Student student : students) {
				System.out.println(student);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}
