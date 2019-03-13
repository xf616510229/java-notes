package zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static util.ConnectionURL.CONNECTION_STRING;

/**
 * @author Feahters
 * @date 2019/3/11
 */
public class Demo {
    

    ZkClient zkClient = null;
    
    @Test
    @Before
    public void createSession() {
        zkClient = new ZkClient(CONNECTION_STRING, 40000);
        System.out.println(zkClient + " -> success");
    }
    
    @Test
    public void createNode() {
        zkClient.create("/zkClient", "1", CreateMode.PERSISTENT); // 创建持久化节点
        zkClient.createPersistent("/zkClient/name", "zk"); // 创建持久化节点
        zkClient.createEphemeral("/zkClient/age", 18); // 创建临时节点
        zkClient.createPersistent("/parent/son1/son1-1/son1-1-1", true); // 可以递归创建父节点, 参2决定
    }

    @Test
    public void getChildNode() {
        List<String> children = zkClient.getChildren("/zkClient");
        System.out.println(children);
    }
    
    @Test
    public void updateData() {
        zkClient.writeData("/zkClient", "111");
    }

    @Test
    public void getDate() {
        Object o = zkClient.readData("/zkClient");
        System.out.println(o.toString());
    }
    
    @Test
    public void watch() throws InterruptedException {
        // 订阅节点data发生变化的watch
        zkClient.subscribeDataChanges("/zkClient", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点名称： " + s + "  节点新值： " + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("节点" + s + "被删除");
            }
        });
        zkClient.writeData("/zkClient", "123");
        TimeUnit.SECONDS.sleep(2);
    }
    
    @Test
    public void deleteNode() {
        zkClient.delete("/zkClient/name"); // 删除指定叶节点
        zkClient.delete("/parent"); // 递归删除
        zkClient.delete("/zkClient"); // 递归删除
    }
}
