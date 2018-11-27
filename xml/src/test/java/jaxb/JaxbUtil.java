package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * 使用javax增强包下的Jaxb工具解析和生成xml
 * @author Feathers
 * @date 2017年4月27日
 */
public class JaxbUtil {
	
	/**
	 * 将obj序列化为xml文件
	 * @param path  要序列化的路径
	 * @param obj   要序列化的对象
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	public static void write(String path, Object obj) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		//设置是否格式化输出
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(obj, new PrintWriter(path));
	}
	
	/**
	 * 将xml文件反序列化为obj
	 * @param path
	 * @param c  反序列化后的对象类型
	 * @return
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 */
	public static Object read (String path, Class<?> c) throws JAXBException, FileNotFoundException {
		Object obj = null;
		JAXBContext context = JAXBContext.newInstance(c);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		obj = unmarshaller.unmarshal(new BufferedReader(new FileReader(path)));
		return obj;
	}
	
}
