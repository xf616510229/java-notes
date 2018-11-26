package jmail;

import org.junit.Test;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * SMTP(Simple Mail Transfer Protocol) 简单邮件发送协议，是一个邮件发送协议
 */
public class SMTPTest {

    // 邮件发送协议
    private final static String PROTOCOL = "smtp";

    // SMTP邮件服务器
    private final static String HOST = "localhost";

    // SMTP邮件服务器默认端口
    private final static String PORT = "25";

    // 是否要求身份认证
    private final static String IS_AUTH = "true";

    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private final static String IS_ENABLED_DEBUG_MOD = "true";

    // 发件人
    private static String FROM = "user01@test.com";

    // 收件人
    private static String TO = "user02@test.com";

    // 初始化连接邮件服务器的会话信息
    private static Properties props = null;

    static {
        props = new Properties();
        // 配置邮件发送协议
        props.setProperty("mail.transport.protocol", PROTOCOL);
        // 配置SMTP服务器
        props.setProperty("mail.smtp.host", HOST);
        // 配置SMTP端口号
        props.setProperty("mail.smtp.port", PORT);
        // 是否要求身份验证
        props.setProperty("mail.smtp.auth", IS_AUTH);
        // 是否启用调试模式
        props.setProperty("mail.debug", IS_ENABLED_DEBUG_MOD);
    }

    @Test
    public void sendSimpleTextMail() throws Exception {
        // 创建Session实例对象
        Session session = Session.getDefaultInstance(props);

        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置发件人
        message.setFrom(new InternetAddress(FROM));
        // 设置邮件主题
        message.setSubject("使用javamail发送简单文本邮件");
        // 设置收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(TO));
        // 设置多个收件人
        //message.setRecipients(MimeMessage.RecipientType.TO, new InternetAddress[]{});
        // 设置发送时间
        message.setSentDate(new Date());
        // 设置纯文本内容为邮件正文
        message.setText("使用SMTP协议发送文本邮件测试!!!");
        // 保存并生成最终的邮件内容
        message.saveChanges();

        // 获得Transport实例对象
        Transport transport = session.getTransport();
        // 打开连接
        transport.connect("user01", "user01");
        // 将message对象传递给transport对象，将邮件发送出去
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接
        transport.close();
    }

    @Test
    public void sendMultiPartMail() throws Exception {
        // 使用配置获取session对象
        Session session = Session.getDefaultInstance(props);

        // 创建消息对象，用于发送 MimeMessage
        MimeMessage message = new MimeMessage(session);
        // 设置发件人
        message.setFrom(new InternetAddress(FROM));
        // 设置收件人地址，可以设置多个收件人
        InternetAddress[] internetAddresses = new InternetAddress[]{
                new InternetAddress(TO)
        };
        // RecipientType.to 主要接收人
        // RecipientType.CC 抄送人
        // RecipientType.BCC 秘密抄送人
        message.addRecipients(Message.RecipientType.TO, internetAddresses);
        // 设置邮件主题
        message.setSubject("我是邮件主题");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件正文内容
        message.setText("我是邮件正文内容");
        // 保存邮件最终内容
        message.saveChanges();

        MimeBodyPart mbp1 = new MimeBodyPart();
        mbp1.setText("我是附件");
        // 把前面定义的msgText中的文字设定为邮件正文的内容
        MimeBodyPart mbp2 = new MimeBodyPart();
        mbp2.setText("actt", "utf-8");
        // 创建附件部分
        Multipart mp = new MimeMultipart();
        // 创建Multipart
        mp.addBodyPart(mbp1);
        mp.addBodyPart(mbp2);
        // 把前面定义的正文和附件都添加到Multipart中
        message.setContent(mp);
        // 添加 Multipart到Message中

        // 获得Transport实例对象
        Transport transport = session.getTransport();
        // 打开连接
        transport.connect("user01", "user01");

        // 将message对象传递给transport对象，将邮件发送出去
//        Transport.send(message,message.getAllRecipients());
        transport.sendMessage(message, message.getAllRecipients());

        System.out.println("message is OK");
    }
}
