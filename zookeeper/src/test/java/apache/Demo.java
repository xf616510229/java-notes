package apache;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.ConnectionURL;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Feahters
 * @date 2019/3/11
 */
public class Demo implements Watcher {


    ZooKeeper zooKeeper = null;

    CountDownLatch countDownLatch = new CountDownLatch(1);
    Stat stat = new Stat();


    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("进入process方法，"
                + " connectionState: " + watchedEvent.getState()
                + " eventType： " + watchedEvent.getType());

        try {
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) { // 连接成功，停止门闩阻塞
                if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                    // 建立连接时
                    countDownLatch.countDown();
                } else if (Event.EventType.NodeCreated.equals(watchedEvent.getType())) {
                    System.out.println("创建节点 watch 被触发 " + watchedEvent.getPath()
                            + " data=" + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println("修改节点数据 watch 被触发 " + watchedEvent.getPath()
                            + " data=" + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                    System.out.println("修改子节点 watch 被触发 " + watchedEvent.getPath() + " data="
                            + " data=" + zooKeeper.getData(watchedEvent.getPath(), true, stat));
                } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                    System.out.println("删除节点 watch 被触发 " + watchedEvent.getPath());
                }
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建session
     */
    @Before
    @Test
    public void createSession() throws Exception {

        // 参1：连接串，集群的所有串
        // 参2：session过期事件
        // 参3：watch，监控机制
        zooKeeper = new ZooKeeper(ConnectionURL.CONNECTION_STRING, 5000, this);
        countDownLatch.await();
        System.out.println("zookeeper客户端连接状态为：" + zooKeeper.getState());
    }

    /**
     * 创建节点
     */
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        String result = zooKeeper.create("/feathers", "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(result);

        if (zooKeeper.exists("/feathers/name", true) == null) { // exist 可以注册监听 
            zooKeeper.create("/feathers/name", "yangsx".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 修改节点
     */
    @Test
    public void setNode() throws KeeperException, InterruptedException {

        Stat result = null;
        if (zooKeeper.exists("/feathers", true) != null) { // exist 可以注册监听
            result = zooKeeper.setData("/feathers", "123".getBytes(), -1);
        }
        System.out.println(result);
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 删除节点
     */
    @Test
    public void deleteNode() throws KeeperException, InterruptedException {
        zooKeeper.getData("/feathers", this, stat);// 注册Watcher, 注意路径， Watcher 是一次性的，所以每次都要注册
        zooKeeper.delete("/feathers/name", -1); // -1 代表不关心版本号
        zooKeeper.delete("/feathers", -1); // -1 代表不关心版本号
        // 无返回值
        Thread.sleep(2000); // 异步操作，需要睡眠
    }

    /**
     * 获取指定节点下的子节点
     */
    @Test
    public void getChildNode() throws KeeperException, InterruptedException  {
        List<String> children = zooKeeper.getChildren("/", true);
        System.out.println(children);
    }

}

