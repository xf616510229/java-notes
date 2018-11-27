package jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 * <p>
 *
 * @author Feathers
 * @date 2017-12-05 14:49
 */
public class TestJdom {

    public static void main(String[] args) throws Exception {

        SAXBuilder builder = new SAXBuilder();

        // 使用多种方式构建document对象
        // Document doc = builder.build(new File("src/test.xml"));
        // Document doc = builder.build(new FileInputStream("src/test.xml"));
        // Document doc = builder.build(new FileReader("src/test.xml"));
        // Document doc = builder.build(new URL("http://localhost:8080/jdomTest/test.xml"));

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("me/feathers/jdom/moudles.xml");
        assert in != null;
        Document doc = builder.build(in);
        //readXmlFile(doc);
        addXmlElement(doc);
    }

    /**
     * 解析xml文件
     */
    public static void readXmlFile(Document doc) throws Exception {

        //获取根元素
        Element root = doc.getRootElement();

        //Element e = root.getChild("person"); //获取第一个子元素
        List<Element> persons = root.getChildren();

        List<Person> personList = new ArrayList<>();
        for (Element person : persons) {
            Person p = new Person();
            p.setId(person.getAttribute("id").getIntValue());
            p.setUsername(person.getChild("username").getValue());
            p.setPassword(person.getChild("password").getValue());
            personList.add(p);
        }
        System.out.println(personList);
    }

    /**
     * 新增节点
     */
    public static void addXmlElement(Document doc) throws Exception {
        Element root = doc.getRootElement(); //获取根元素

        Element newEle = new Element("person");//设置新增的person的信息
        newEle.setAttribute("id", "88888");

        Element chiledEle = new Element("username"); //设置username的信息
        chiledEle.setText("addUser");
        newEle.addContent(chiledEle);

        Element chiledEle2 = new Element("password"); //设置password的信息
        chiledEle2.setText("addPassword");
        newEle.addContent(chiledEle2);

        root.addContent(newEle);

        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getCompactFormat().setEncoding("GBK"));//设置UTF-8编码,理论上来说应该不会有乱码，但是出现了乱码,故设置为GBK
        out.output(doc, new FileWriter("F:/temp/test.xml")); //写文件
    }

    /**
     * 更新节点
     * @param doc
     * @throws Exception
     */
    public static void updateXmlElement(Document doc) throws Exception {
        Element root = doc.getRootElement(); //获取根元素

        //循环person元素并修改其id属性的值
        for(Element el : root.getChildren("person")){
            el.setAttribute("id","haha");
        }
        //循环设置username和password的文本值和添加属性
        for(Element el: root.getChildren()){
            el.getChild("username").setAttribute("nameVal", "add_val").setText("update_text");
            el.getChild("password").setAttribute("passVal", "add_val").setText("update_text");
        }

        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getCompactFormat().setEncoding("GBK"));//设置UTF-8编码,理论上来说应该不会有乱码，但是出现了乱码,故设置为GBK
        out.output(doc, new FileWriter("F:/temp/test.xml")); //写文件
    }

    /**
     * 删除节点和属性
     * @param doc
     * @throws Exception
     */
    public static void deleteXmlElement(Document doc) throws Exception {
        Element root = doc.getRootElement(); //获取根元素

        List<Element> personList = root.getChildren("person");

        //循环person元素,删除person的id为1的id属性以及username子节点
        for(Element el : personList){
            if(null!=el.getAttribute("id") && "1".equals(el.getAttribute("id").getValue())){
                el.removeAttribute("id");
                el.removeChild("username");
            }
        }

        //循环person元素,删除person的id为2的节点
        for(int i=0; i<personList.size(); i++){
            Element el = personList.get(i);
            if(null!=el.getAttribute("id") &&  "2".equals(el.getAttribute("id").getValue())){
                root.removeContent(el);//从root节点上删除该节点
                //警告：此处删除了节点可能会使personList的长度发生变化而发生越界错误,故不能写成for(int i=0,len=personList.size(); i<len; i++)
            }
        }

        XMLOutputter out = new XMLOutputter();
        out.setFormat(Format.getCompactFormat().setEncoding("GBK"));//设置UTF-8编码,理论上来说应该不会有乱码，但是出现了乱码,故设置为GBK
        out.output(doc, new FileWriter("F:/temp/test.xml")); //写文件
    }
}

/*

Document    build(java.io.File file)       //This builds a document from the supplied filename.
Document    build(org.xml.sax.InputSource in)   //This builds a document from the supplied input source.
Document    build(java.io.InputStream in)    //This builds a document from the supplied input stream.
Document    build(java.io.InputStream in, java.lang.String systemId)   //This builds a document from the supplied input stream.
Document    build(java.io.Reader characterStream)   //This builds a document from the supplied Reader.
Document    build(java.io.Reader characterStream, java.lang.String systemId)  //This builds a document from the supplied Reader.
Document    build(java.lang.String systemId)  //This builds a document from the supplied URI.
Document    build(java.net.URL url)   //This builds a document from the supplied URL.

 */