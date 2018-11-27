package commons.pool;

/**
 * FtpClientConfig
 * FtpClient配置实体
 * <p>
 *
 * @author Feathers
 * @date 2018-05-17 23:52
 */
public class FtpClientConfig {

    /**
     * 主机地址
     */
    private String host;
    /**
     * 端口号
     */
    private int port;
    /**
     * 登陆用户名
     */
    private String username;
    /**
     * 登陆密码
     */
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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
}
