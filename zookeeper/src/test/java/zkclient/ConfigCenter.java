package zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.junit.Test;
import util.ConnectionURL;

import java.io.*;

/**
 * 配置中心实现
 *    粗粒度，以文件名为key，文件内容为data，放入至zookeeper中，客户端监控data数据是否发生变化
 * @author Feahters
 * @date 2019/3/12
 */
public class ConfigCenter {

    @Test
    public void publishConfig() throws IOException {
        ConfigCenterManager configCenterManager = new ConfigCenterManager();
        configCenterManager.publishConfigToZookeeper("db.properties");
    }

    @Test
    public void startApp() throws IOException {
        Application application = new Application("应用程序1", "db.properties");
        System.in.read();
    }
    
}


class ConfigCenterManager {

    private ZkClient zkClient = new ZkClient(ConnectionURL.CONNECTION_STRING, 5 * 1000, 5 * 1000);

    private static final String BASE_CONFIG_PATH = "config/";

    /**
     * 将配置数据发布到zookeeper中
     */
    public void publishConfigToZookeeper(String filename) throws IOException {

        // 读取配置文件
        InputStream fileInputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(BASE_CONFIG_PATH + filename);
        if (fileInputStream == null) {
            throw new RuntimeException("错误的配置文件");
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while (-1 != (n = fileInputStream.read(buffer))) {
            baos.write(buffer, 0, n);
        }
        if (!zkClient.exists("/config")) {
            zkClient.createPersistent("/config");
        }

        System.out.println(baos.toString("utf-8"));
        if (!zkClient.exists("/config/" + filename)) {
            zkClient.createPersistent("/config/" + filename, baos.toString("utf-8"));
        } else {
            zkClient.writeData("/config/" + filename, baos.toString());
        }
    }
}

/**
 * 使用配置中心配置文件的应用程序
 */
class Application {

    private ZkClient zkClient = new ZkClient(ConnectionURL.CONNECTION_STRING, 5 * 1000, 5 * 1000);

    /**
     * 配置文件缓存路径
     */
    private static final String CACHE_CONFIG_PATH = "D:/app/config/";

    Application(String appName, String file) throws FileNotFoundException {
        zkClient.subscribeDataChanges("/config/" + file, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("---------- " + appName + " 配置文件发生变化，开始重新加载 -------------");
                loadConfigFromConfigCenter(file);
                System.out.println("---------- " + appName + "  加载完毕 -------------");
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {

            }
        });
        loadConfigFromConfigCenter(file);
    }

    /**
     * 从配置中心(zookeeper)加载配置文件
     */
    private void loadConfigFromConfigCenter(String fileName) throws FileNotFoundException {
        Object configContent;
        try {
            configContent = zkClient.readData("/config/" + fileName);
        } catch (ZkNoNodeException e) {
            System.out.println(" 配置文件不存在！！ ");
            return;
        }

        File file = new File(CACHE_CONFIG_PATH + fileName);
        
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(configContent.toString());
        printWriter.close();
        
    }

}