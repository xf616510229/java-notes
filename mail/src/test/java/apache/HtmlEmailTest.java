package apache;

import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.junit.Before;
import org.junit.Test;

import javax.mail.internet.InternetAddress;
import java.net.URL;
import java.util.ArrayList;

public class HtmlEmailTest {

    private HtmlEmail htmlEmail;

    @Before
    public void initSender() throws Exception {
        htmlEmail = new HtmlEmail();
        htmlEmail.setCharset("UTF-8"); // 设置邮件编码，否则有可能乱码
        htmlEmail.setHostName("SMTP.163.com"); // SMTP 发送服务器
        htmlEmail.setAuthentication("xf616510229@163.com", "xiangfan521"); // 验证信息，用户名/密码（授权码）
        htmlEmail.setFrom("xf616510229@163.com", "Feathers"); // 设置发件人
        htmlEmail.addTo("616510229@qq.com"); // 添加收件人，可以多个
        htmlEmail.addCc("xf616510229@163.com"); // 添加抄送人，可以多个， 如果163返回554 DT:SPM，可以抄送自己一份
        htmlEmail.setBcc(new ArrayList<InternetAddress>() {{
            add(new InternetAddress("18361507620@139.com")); // 可以多个
        }});
    }

    @Test
    public void sendHtml() throws Exception{
        htmlEmail.setHtmlMsg("<h1>我是html</h1>");
        htmlEmail.setTextMsg("我不是html"); // 设置html ，我就没用了
        htmlEmail.embed(new URL("http://www.baidu.com/img/bd_logo1.png?qua=high"), "小黄图"); // 嵌入图片
        htmlEmail.send();
    }

}
