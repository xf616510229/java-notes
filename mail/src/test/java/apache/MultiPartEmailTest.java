package apache;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.junit.Before;
import org.junit.Test;

import javax.mail.internet.InternetAddress;
import java.util.ArrayList;

public class MultiPartEmailTest {

    private MultiPartEmail multiPartEmail;

    @Before
    public void initSender() throws Exception {
        multiPartEmail = new MultiPartEmail();
        multiPartEmail.setCharset("UTF-8"); // 设置邮件编码，否则有可能乱码
        multiPartEmail.setHostName("SMTP.163.com"); // SMTP 发送服务器
        multiPartEmail.setAuthentication("xf616510229@163.com", "xxxx"); // 验证信息，用户名/密码（授权码）
        multiPartEmail.setFrom("xf616510229@163.com", "Feathers"); // 设置发件人
        multiPartEmail.addTo("616510229@qq.com"); // 添加收件人，可以多个
        multiPartEmail.addCc("xf616510229@163.com"); // 添加抄送人，可以多个， 如果163返回554 DT:SPM，可以抄送自己一份
        //multiPartEmail.setCc()
        //multiPartEmail.addBcc("test@qq.com"); // 添加暗抄送（盲抄送），盲抄送是指各个收件人只查看到邮件，而不能看到其他收件人的地址
        multiPartEmail.setBcc(new ArrayList<InternetAddress>() {{
            add(new InternetAddress("18361507620@139.com")); // 可以多个
        }});
        // multiPartEmail.setBounceAddress("xxx"); // 如果邮件无法投递，将会将邮件发送给该地址
    }


    /**
     * 附件邮件发送
     */
    @Test
    public void sendMulti() throws Exception {
        EmailAttachment attachment1 = new EmailAttachment();
        attachment1.setDisposition(EmailAttachment.INLINE); // 类型为内嵌资源, 内嵌显示乱码，待解决
        attachment1.setURL(MultiPartEmailTest.class.getClassLoader().getResource("apache/文本附件.txt"));
        attachment1.setName("TXT.txt");
        EmailAttachment attachment2 = new EmailAttachment(); // 类型为外置附件
        attachment2.setURL(MultiPartEmailTest.class.getClassLoader().getResource("apache/word附件.docx"));
        attachment2.setDescription("这是一个word文档，重点看"); // 附件描述
        attachment2.setName("WORD.docx"); // 附件名称，注意拓展名一致

        multiPartEmail.attach(attachment1);
        multiPartEmail.attach(attachment2);
        //multiPartEmail.setMsg("我是内容！！"); // 设置msg后，内嵌资源将会以附件的方式展示在邮件中，即内嵌资源失效
        multiPartEmail.send();
    }

}
