package apache;

import org.apache.zookeeper.*;
import util.ConnectionURL;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 分布式锁实现
 *
 * @author Feahters
 * @date 2019/3/12
 */
public class ZookeeperLock implements Lock {

    private static ZooKeeper getInstance() throws IOException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(ConnectionURL.CONNECTION_STRING, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    latch.countDown();
                }
            }
        });
        latch.await();
        return zooKeeper;
    }

    private ZooKeeper zooKeeper;

    /**
     * 会话超时时间
     */
    private int sessionTimeout;

    /**
     * 根节点
     */
    private static final String ROOT_LOCKS = "/locks";

    /**
     * 记录当前锁的节点id
     */
    private String lockId;

    /**
     * 节点数据
     */
    private final byte[] data = {1, 2};

    private CountDownLatch latch = new CountDownLatch(1);

    public ZookeeperLock() throws IOException, InterruptedException {
        this.zooKeeper = getInstance();
        this.sessionTimeout = 5000;
    }

    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 这是阻塞锁，阻塞方式为 latch
     */
    @Override
    public boolean tryLock() {
        try {
            lockId = zooKeeper.create(ROOT_LOCKS + "/", data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + " 成功创建节点 " + lockId + " 开始去竞争锁");

            List<String> children = zooKeeper.getChildren(ROOT_LOCKS, true);// 获取跟节点下的所有子节点
            SortedSet<String> sortedSet = new TreeSet<>(children);

            // 拿到最小的节点
            String first = sortedSet.first();
            if (lockId.equals(first)) {
                System.out.println(Thread.currentThread().getName() + " 成功获取锁");
                return true;
            }

            SortedSet<String> lessThanLockId = sortedSet.headSet(lockId); // 获取比lockId更小的新的集合
            if (!lessThanLockId.isEmpty()) {
                String previousLock = lessThanLockId.last(); // 获取新的小集合的最后一个元素，这个元素就是比lockId小的上一个节点
                // 如果超过会话时间，或者节点被删除，则停止await
                zooKeeper.exists(previousLock, new LockWatcher(latch));
                latch.await(sessionTimeout, TimeUnit.MILLISECONDS);
            }
            return true;
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        try {
            zooKeeper.delete(lockId, -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private static class LockWatcher implements Watcher {
        private CountDownLatch latch;

        LockWatcher(CountDownLatch latch) {
            this.latch = latch;
        }

        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeDeleted) {
                latch.countDown();
            }
        }
    }
}
