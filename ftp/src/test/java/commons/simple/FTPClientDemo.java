package commons.simple;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * FTPClient
 * <p>
 *
 * @author Feathers
 * @date 2017-11-29 18:00
 */
public class FTPClientDemo {

    private static final String FTP_HOST = "10.35.10.156";
    private static final Integer FTP_PORT = 21;
    private static final String FTP_USERNAME = "ftptest";
    private static final String FTP_PASSWORD = "ftptest";
    private static final String FTP_REMOTE_ROOT_PATH = "/";

    /**
     * 下载ftp上的文件
     */
    @Test
    public void downFile() {
        // 创建一个ftp客户端
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(FTP_HOST, FTP_PORT);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            //登录
            ftp.login(FTP_USERNAME, FTP_PASSWORD);
            // 获取FTP响应代码
            reply = ftp.getReplyCode();
            // 如果响应代码完成响应
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            //转移到FTP服务器目录
            ftp.changeWorkingDirectory(FTP_REMOTE_ROOT_PATH);
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals("share.txt")) {
                    File localFile = new File("D:ftppool/local/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /**
     * 上传文件
     */
    @Test
    public void uploadFile() {
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(FTP_HOST, FTP_PORT);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);//登录
            ftp.setControlEncoding("GBK");
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            ftp.changeWorkingDirectory("/");
            InputStream in = new FileInputStream("C:/Users/Administrator/Desktop/javamail.txt");

            // 上传文件前,对文件名称进行编码转换
            String filename = "我是新上传的文件.txt";
            filename = new String(filename.getBytes(Charset.defaultCharset()), StandardCharsets.UTF_8);
            ftp.storeFile(filename, in);
            in.close();
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

}
