package dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 使用dom4j 写入xml
// sax解析不支持写入

public class TestDom4jWritter {
	public static void main(String[] args) {
		
		// 使用Dom4j写入xml
		
		// 创建要写入的对象
		List<Moudle> list = new ArrayList<>();
		Moudle m1 = new Moudle(1L, "jack", 15, new Date());
		Moudle m2 = new Moudle(2L, "rosi", 16, new Date());
		Moudle m3 = new Moudle(3L, "tom", 18, new Date());
		list.add(m1);
		list.add(m2);
		list.add(m3);
		
		// 创建一个文档树
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("moudles"); //创建根元素
		for (Moudle moudle : list) {
			Element ee = root.addElement("moudle");
			ee.addAttribute("id", String.valueOf(moudle.getId())); // 添加id
			Element name = ee.addElement("name");
			name.setText(moudle.getName());
			Element age = ee.addElement("age");
			age.setText(String.valueOf(moudle.getAge()));
			Element birthday = ee.addElement("birthday");
			birthday.setText(moudle.getBirthday().toString());
		}
		
		// 创建一个Outputformat
		OutputFormat format = new OutputFormat();
		format.setNewlines(true);
		format.setIndent(true);
//		format.setIndent("\t\t");
		
		// 创建XMLWriter对象，进行写入
		XMLWriter writer = null;
		try {
			writer = new XMLWriter(new FileWriter("F:/temp/moudles_write.xml"), format);
			writer.write(document); //将文档树写入
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				assert writer != null;
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
