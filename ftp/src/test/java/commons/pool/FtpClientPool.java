package commons.pool;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;

/**
 * FtpClientPool
 * <p>
 *
 * @author Feathers
 * @date 2018-05-17 23:55
 */
public class FtpClientPool extends GenericKeyedObjectPool<FtpClientConfig, FTPClient> {

    public FtpClientPool(KeyedPooledObjectFactory<FtpClientConfig, FTPClient> factory) {
        super(factory);
    }
}
