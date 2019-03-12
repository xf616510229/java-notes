package curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Before;
import org.junit.Test;
import util.ConnectionURL;

import java.util.concurrent.TimeUnit;

/**
 * curator-recipes 提供了常用场景的封装
 *
 * @author Feahters
 * @date 2019/3/12
 */
public class Recipes {
    private CuratorFramework curatorFramework;

    @Before
    public void createSession() {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ConnectionURL.CONNECTION_STRING)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
    }

    /**
     * 选举：
     * LeaderLatch
     * LeaderSelector
     */
    @Test
    public void masterSelect() {

        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, "/custom_master_path", new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.out.println(" 获取leader成功");
                TimeUnit.SECONDS.sleep(2);
            }
        });

        leaderSelector.autoRequeue();
        leaderSelector.start(); // 开始选举
    }

}