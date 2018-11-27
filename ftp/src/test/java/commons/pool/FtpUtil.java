package commons.pool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

import java.io.IOException;

/**
 * FtpClient工具类
 * <p>
 *
 * @author Feathers
 * @date 2018-05-17 22:13
 */
public class FtpUtil {

    public class FtpUtilConfig {
        /**
         * ftp客户端
         */
        private FTPClient ftpClient;

        /**
         * 主机地址
         */
        private String hostname;
        /**
         * 端口号
         */
        private int port = 21;
        /**
         * 登陆用户名
         */
        private String username = "";
        /**
         * 登陆密码
         */
        private String password = "";
        /**
         * 远程路径
         */
        private String remoteDir = "/";

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRemoteDir() {
            return remoteDir;
        }

        public void setRemoteDir(String remoteDir) {
            this.remoteDir = remoteDir;
        }
    }

    public static FtpUtil build() {
        return new FtpUtil();
    }

    /**
     * ftpClient实例
     */
    private FTPClient ftpClient;
    /**
     * ftp配置
     */
    private FtpUtilConfig config;

    private FtpUtil() {
        this.config = new FtpUtilConfig();
    }

    public FtpUtil(FtpUtilConfig config) {
        this.config = config;
    }

    private void checkConfig() {
        if (this.config == null ||
                this.config.getHostname() == null) {
            throw new IllegalArgumentException("FTP HOST_ADDRESS 为 null");
        }
    }

    public void login() throws Exception {
        ftpClient = new FTPClient();
        String hostname = this.config.getHostname();
        int port = this.config.getPort();
        String username = this.config.getUsername();
        String password = this.config.getPassword();
        String remoteDir = this.config.getRemoteDir();

        ftpClient.configure(new FTPClientConfig());
        ftpClient.setDefaultPort(21);
        ftpClient.setControlEncoding("UTF-8");
        try {
            ftpClient.connect(hostname, port);
        } catch (IOException e) {
            throw new Exception("FTP连接失败，请检查网络、IP、端口");
        }
        if (!ftpClient.login(username, password)) {
            throw new Exception("FTP登陆失败，请检测登陆用户名和密码是否正确!");
        }
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.changeWorkingDirectory(remoteDir);
    }


}

