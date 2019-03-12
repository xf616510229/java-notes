package zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static util.ConnectionURL.CONNECTION_STRING;

/**
 * Master选举服务
 *
 * @author Feahters
 * @date 2019/3/12
 */
public class MasterSelectorService implements IZkDataListener {

    private ZkClient zkClient;

    /**
     * 参与选举的客户端
     */
    private UserCenter userCenter;

    /**
     * 选举记录的路径
     */
    private String MASTER_PATH = "/master";

    /**
     * 选举服务运行状态
     */
    private boolean isRunning = false;

    public MasterSelectorService(ZkClient zkClient, UserCenter userCenter) {
        this.zkClient = zkClient;
        this.userCenter = userCenter;
    }

    /**
     * 启动服务进行选举监听和选举
     */
    public void start() {
        if (isRunning) {
            throw new RuntimeException("服务已经启动");
        }

        isRunning = true;
        // 启动选举监听
        zkClient.subscribeDataChanges(MASTER_PATH, this);

        // 自动选举一次，因为第一次启动，有可能所有节点都没有进行选举
        choseMaster();
    }

    /**
     * 执行选举
     */
    private void choseMaster() {

        System.out.println("触发选举 " + userCenter.getId());

        if (!isRunning) {
            return;
        }

        try {
            zkClient.create(MASTER_PATH, userCenter, CreateMode.EPHEMERAL);
            System.out.println("选举成功：" + userCenter.getId());
        } catch (ZkNodeExistsException e) { // 如果抛出ZkNodeExistsException，说明节点已经存在，master已经选举成功
            // 为了确保master确实已经选举成功，将会再查询一次
            if (zkClient.readData(MASTER_PATH, true) == null) { // 如果没有选举成功，重新选举一次
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                choseMaster();
            }
        }
    }

    /**
     * 停止选举与选举监听
     */
    public void stop() {
        if (isRunning) {
            isRunning = false;
            zkClient.unsubscribeDataChanges(MASTER_PATH, this);  // 解绑监听
        }
    }

    @Override
    public void handleDataChange(String s, Object o) throws Exception {

    }

    @Override
    public void handleDataDeleted(String s) throws Exception {
        // 当 master 被删除或者失效时触发
        // 原master失效，将当前的节点参与到master选举中
        choseMaster();
    }
}

/**
 * 参与选举的客户端抽象
 */
class UserCenter implements Serializable {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserCenter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

class Test {

    /**
     * 启动后，通过删除/master节点模拟节点断开。查看是否重新选举
     */
    public static void main(String[] args) throws IOException {
        List<UserCenter> userCenters = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserCenter userCenter = new UserCenter();
            userCenter.setId("user" + i);
            userCenter.setName("user" + i);
            userCenters.add(userCenter);
        }

        for (int i = 0; i < userCenters.size(); i++) {
            ZkClient zkClient = new ZkClient(CONNECTION_STRING, 5*1000, 5000);
            MasterSelectorService selectorService = new MasterSelectorService(zkClient, userCenters.get(i));
            selectorService.start(); // 开始选举
        }

        System.in.read();
    }

}
