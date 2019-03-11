package curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.ConnectionURL.CONNECTION_STRING;

/**
 * Curator 是 Netflix 开发的 Zookeeper 客户端
 * 相比zkClient，异常处理更优美，使用更优雅
 * Curator 提供了各种应用场景的封装：
 * <p>
 * curator-framework 提供了 fluent 风格的api
 * curator-recipes
 *
 * @author Feahters
 * @date 2019/3/11
 */
public class Demo {

    private CuratorFramework curatorFramework;

    @Before
    @Test
    public void createSession() throws IOException {
        // 创建会话的两种方式
        curatorFramework = CuratorFrameworkFactory
                .newClient(CONNECTION_STRING, 5000, 5000, new ExponentialBackoffRetry(1000, 3));
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(CONNECTION_STRING)
                .retryPolicy(new ExponentialBackoffRetry(5000, 3))
                .namespace("curator") // 命名空间，此客户端的所有操作都会在这个节点下
                .build();
        // 调用start 开启客户端，连接zookeeper
        curatorFramework.start();
        
        /*
        
        curator 的四种重试策略：
        1. ExponentialBackoffRetry(间隔事件，重试次数) 衰减重试策略： 每隔一定时间重试，时间过短可能导致仍然失败，有重试次数限制可以预防重试占用过多资源
        2. RetryNTime 指定最大重试次数
        3. RetryOneTime 仅重试一次
        4. RetryUnitilElapsed 一直重试到规定的事件
         */
    }

    @Test
    public void createNode() throws Exception {
        String result = curatorFramework.create()
                .creatingParentsIfNeeded() // 递归创建
                .withMode(CreateMode.PERSISTENT) // 持久化
                .forPath("/name", "curator data".getBytes()); // 还可以存储对象，使用序列化
        System.out.println(result); // 节点路径名称
    }

    @Test
    public void getNodeData() throws Exception {
        Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData()
                .storingStatIn(stat)
                .forPath("/name");
        System.out.println(new String(bytes));
        System.out.println(stat);
    }

    @Test
    public void updateNodeData() throws Exception {
        Stat stat = curatorFramework.setData()
                .forPath("/name", "lisi".getBytes());
        System.out.println(stat);
    }

    @Test
    public void deleteNode() throws Exception {
        curatorFramework.delete()
                .deletingChildrenIfNeeded()
                .forPath("/");
    }

    /**
     * 异步创建
     */
    @Test
    public void asyncOpr() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        System.out.println(Thread.currentThread().getName() + " --- " + curatorEvent.getResultCode() + " ->" + curatorEvent.getType());
                        latch.countDown();
                    }
                }, executor)
                .forPath("/async", "123".getBytes());
        latch.await();
        executor.shutdown();
    }

    /**
     * 事务，curator独有
     * and 的两件事在同一事务上
     * <p>
     * 执行失败，不会插入，setData 节点不存在
     */
    @Test
    public void transaction() throws Exception {
        Collection<CuratorTransactionResult> collection
                = curatorFramework.inTransaction()
                .create()
                .forPath("/trans", "111".getBytes())
                .and()
                .setData()
                .forPath("/transx", "111".getBytes())
                .and()
                .commit(); // 返回值 Collection 用于作为返回结果
        System.out.println(collection);
    }

    /**
     * 节点事件
     * <p>
     * 三种watcher做节点监听：
     * 1. PathCache 监视某个路径下子节点的增删改
     * 2. NodeCache 监视某个节点的增删改
     * 3. TreeCache PathCache+NodeCache 监视路径下的增删改事件，还会缓存路径下的所有子节点的数据
     */
    @Test
    public void eventNodeCache() throws Exception {

        NodeCache cache = new NodeCache(curatorFramework, "/eventNodeCache", false);
        cache.start(true);
        cache.getListenable().addListener(() -> System.out.println("节点数据发生变化, 变化后的结果为" + new String(cache.getCurrentData().getData()))); // 将新的数据缓存到了Cache中，提高了效率

        curatorFramework.setData().forPath("/eventNodeCache", "菲菲".getBytes());
        System.in.read();
    }


    /**
     * 会监听多次
     */
    @Test
    public void eventPathNodeCache() throws Exception {

        PathChildrenCache cache = new PathChildrenCache(curatorFramework, "/eventPathNodeCache", true);
        cache.start(PathChildrenCache.StartMode.NORMAL);
        //PathChildrenCache.StartMode.NORMAL   // 初始化时为空
        //PathChildrenCache.StartMode.BUILD_INITIAL_CACHE  // 在方法返回之前调用rebuild操作
        //PathChildrenCache.StartMode.POST_INITIALIZED_EVENT  

        cache.getListenable().addListener((curatorFramework, pathChildrenCacheEvent) -> {
            switch (pathChildrenCacheEvent.getType()) {
                case CHILD_ADDED:
                    System.out.println("增加子节点");
                    break;
                case CHILD_REMOVED:
                    System.out.println("删除子节点");
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新子节点");
                    break;
                default:
                    break;
            }
        });

        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/eventPathNodeCache/test", "test".getBytes());
        TimeUnit.SECONDS.sleep(2);
        curatorFramework.setData().forPath("/eventPathNodeCache/test", "test".getBytes());
        TimeUnit.SECONDS.sleep(2);
        curatorFramework.delete().forPath("/eventPathNodeCache/test");
        TimeUnit.SECONDS.sleep(2);
    }

}
