package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

// SAX(Simple API for XML),是指一种接口，或者一个软件包

/**
 * 使用sax方式解析xml
 * 
 * DOM:文档驱动。
 * 		DOM在解析文件之前把整个文档装入内存，
 * 		处理大型文件时其性能很差，是由于DOM
 * 		的树结构所造成的，此结构占用的内存较多。
 * 
 * SAX:事件驱动型的XML解析方式。顺序读取XML文件，
 * 		不需要一次全部装载整个文件。
 * 		当遇到像文件开头，文档结束，或者标签开头与标签结束时，
 * 		会触发一个事件，用户通过在其回调事件中写入处理代码来
 * 		处理XML文件，适合对XML的顺序访问，【且是只读的】。
 * 		不能使用sax写xml，即不能完成序列化，只能完成反序列化
 * 
 * 
 * 由于移动设备的内存资源有限，SAX的顺序读取方式更适合移动开发!!!!
 * 
 * SAX解析XML步骤
 *		①创建XML解析处理器。
 *		②创建SAX解析器。
 *		③将XML解析处理器分配给解析器。
 *		④对文档进行解析，将每个事件发送给处理器。
 * 
 * @author Feathers
 * @date 2017年4月25日
 */
public class SaxParserHandler extends DefaultHandler { // 这是一个sax解析处理器

	private Lesson lesson;
	private Set<Lesson> lessons;
	private Student student;
	private Set<Student> students;
	private String preTag;

	@Override
	public void startDocument() throws SAXException {
		lessons = new HashSet<Lesson>();
		students = new HashSet<Student>();
	}
	
	@Override
	public void startElement(String uri, String localName, String name, Attributes attr) throws SAXException {
		if ("student".equals(name)) {
			student = new Student();
		}
		if ("lesson".equals(name)) {
			lesson = new Lesson();
		}
		preTag = name;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (student != null) {
			String data = new String(ch, start, length);
			if ("name".equals(preTag)) {
				student.setName(data);
			}
			if ("sex".equals(preTag)) {
				student.setSex(data);
			}
			if ("lessonName".equals(preTag)) {
				lesson.setLessonName(data);
			}
			if ("lessonScore".equals(preTag)) {
				lesson.setLessonScore(Integer.parseInt(data));
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {
		if (student != null && "student".equals(name)) {
			student.setLessons(lessons);
			students.add(student);
			student = null;
			lessons = new HashSet<Lesson>();
		}
		if (lesson != null && "lesson".equals(name)) {
			lessons.add(lesson);
			lesson = null;
		}
		preTag = null;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public Set<Lesson> getLessons() {
		return lessons;
	}
}
