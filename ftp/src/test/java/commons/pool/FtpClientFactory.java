package commons.pool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * FtpClientFactory
 * FtpClient工厂类，用于生成ftpClient实例
 * <p>
 *
 * @author Feathers
 * @date 2018-05-17 23:49
 */
public class FtpClientFactory implements KeyedPooledObjectFactory<FtpClientConfig, FTPClient> {

    @Override
    public PooledObject<FTPClient> makeObject(FtpClientConfig key) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(key.getHost(), key.getPort());
        ftpClient.login(key.getUsername(), key.getPassword());
        return new DefaultPooledObject<>(ftpClient);
    }

    @Override
    public void destroyObject(FtpClientConfig key, PooledObject<FTPClient> p) throws Exception {
        FTPClient ftpClient = p.getObject();
        ftpClient.logout();
    }

    @Override
    public boolean validateObject(FtpClientConfig key, PooledObject<FTPClient> p) {
        return false;
    }

    @Override
    public void activateObject(FtpClientConfig key, PooledObject<FTPClient> p) throws Exception {
        
    }

    @Override
    public void passivateObject(FtpClientConfig key, PooledObject<FTPClient> p) throws Exception {

    }
}
