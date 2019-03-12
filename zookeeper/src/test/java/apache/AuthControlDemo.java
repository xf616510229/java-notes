package apache;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static util.ConnectionURL.CONNECTION_STRING;

/**
 * 权限控制
 *
 * @author Feahters
 * @date 2019/3/11
 */
public class AuthControlDemo {
    
    /**
     * 设置权限的数据
     */
    @Test
    public void testAuthControlProduct() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(CONNECTION_STRING, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();

        ACL aclUP = new ACL(ZooDefs.Perms.CREATE, new Id("digest", "root:root"));
        ACL aclIP = new ACL(ZooDefs.Perms.CREATE, new Id("ip", "localhost"));
        List<ACL> acls=new ArrayList<ACL>(){{
            add(aclIP);
            add(aclUP);
        }};

        zooKeeper.create("/auth1","123".getBytes(),acls,CreateMode.PERSISTENT);
        zooKeeper.addAuthInfo("digest","root:root".getBytes());
        zooKeeper.create("/auth1","123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zooKeeper.create("/auth1/auth1-1","123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL,CreateMode.EPHEMERAL);
    }

    @Test
    public void testAuthControlClient() throws Exception {
        
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ZooKeeper zooKeeper = new ZooKeeper(CONNECTION_STRING, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();

        zooKeeper.delete("/auth1",-1);

        // acl (create /delete /admin /read/write)
        //权限模式： ip/Digest（username:password）/world/super
    }
}
