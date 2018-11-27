package dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import util.DateUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 对moudles.xml文件进行解析  读取！！！

@SuppressWarnings("all")
public class TestDom4jParser {
	
	public static void main(String[] args) {
		
		// 1. 获取文档输入流
        InputStream in = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream("me/feathers/dom4j/moudles.xml");

        // 2. 根据文档输入流，创建倒置的文档树
		SAXReader sr = new SAXReader();
		
		List<Moudle> list = new ArrayList<>();
		try {
		    // 根据流 获取Document对象
			Document doc = sr.read(in);
			
			// 3. 获取根元素
			Element rootEle = doc.getRootElement();
			
			// 4. 获取二级元素 moudle
			List eleList = rootEle.elements();
			for (Object o : eleList) {
				Moudle m = new Moudle();
				
				Element ee = (Element) o;
				// 这里只有一个attribute 为 id
				// System.out.println(ee.attributeCount());

				// 根据属性名获取属性值 获取id
				Long id = Long.valueOf(ee.attribute("id").getValue());
				m.setId(id);
				
				// 获取子标签
				Element nameEle = ee.element("name");
				String name = nameEle.getStringValue();
				m.setName(name);
				
				Element birEle = ee.element("birthday");
				Date birthday = DateUtil.parse("yyyy-MM-dd", birEle.getStringValue());
				m.setBirthday(birthday);
				
				Element ageEle = ee.element("age");
				Integer age = Integer.valueOf(ageEle.getStringValue());
				m.setAge(age);

				list.add(m);
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		System.out.println(list);
	}
	
}
