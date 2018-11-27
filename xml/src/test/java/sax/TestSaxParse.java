package sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;


public class TestSaxParse {
	public static void main(String[] args) {
		
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("me/feathers/sax/student.xml");
		// 创建xml解析处理器
		SaxParserHandler handler = new SaxParserHandler();
		
		// 创建一个xml解析器
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		// 创建一个SAX解析器
		try {
			SAXParser parser = factory.newSAXParser();
			
			// 将xml处理器分配给sax解析器
			// 对文档进行解析，将每个事件发送给处理器
			parser.parse(in, handler);
			in.close();
			
			System.out.println(handler.getStudents());
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
