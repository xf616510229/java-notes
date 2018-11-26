package apache;

import org.apache.commons.mail.SimpleEmail;
import org.junit.Before;
import org.junit.Test;

import javax.mail.internet.InternetAddress;
import java.util.ArrayList;

public class SimpleEmailTest {

    private SimpleEmail simpleEmail;


    @Before
    public void initSender() throws Exception {
        simpleEmail = new SimpleEmail();
        simpleEmail.setCharset("UTF-8"); // 设置邮件编码，否则有可能乱码
        simpleEmail.setHostName("SMTP.163.com"); // SMTP 发送服务器
        simpleEmail.setAuthentication("xf616510229@163.com", "xiangfan521"); // 验证信息，用户名/密码（授权码）
        simpleEmail.setFrom("xf616510229@163.com", "Feathers"); // 设置发件人
        simpleEmail.addTo("616510229@qq.com"); // 添加收件人，可以多个
        simpleEmail.addCc("yangsx@c-platform.com"); // 添加抄送人，可以多个
        //simpleEmail.setCc()
        //simpleEmail.addBcc("test@qq.com"); // 添加暗抄送（盲抄送），盲抄送是指各个收件人只查看到邮件，而不能看到其他收件人的地址
        simpleEmail.setBcc(new ArrayList<InternetAddress>() {{
            add(new InternetAddress("18361507620@139.com")); // 可以多个
        }});
        // simpleEmail.setBounceAddress("xxx"); // 如果邮件无法投递，将会将邮件发送给该地址
    }

    /**
     * 简单文本发送
     */
    @Test
    public void testSendText() throws Exception {
        simpleEmail.setSubject("这是一封测试邮件,只有普通文本"); // 设置邮件主题
        simpleEmail.setMsg("我是邮件内容 \n 哈哈哈"); // 设置邮件内容
        simpleEmail.send();
    }

    /**
     * 发送 MIME类型 html类型
     */
    @Test
    public void testSendHtml() throws Exception {
        simpleEmail.setSubject("这是一封测试邮件，包含超文本");
        simpleEmail.setContent("<html><body><h1>我是标题</h1><p>我是一个html邮件</p></body></html>", "text/html");
        simpleEmail.send();
    }

}
