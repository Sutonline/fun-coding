package cn.kevin.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author yongkang.zhang
 * created at 13/08/2018
 */
/*@Component*/
public class ZookeeperComponent implements Watcher, InitializingBean {

    private ZooKeeper zooKeeper;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    private ZookeeperConfiguration zookeeperConfiguration;


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("Watch received event!");
            countDownLatch.countDown();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        connectZookeeper(this.zookeeperConfiguration.getHost());
    }

    /**
     * session的timeout
     * @param host 连接主机，comma分割
     *
     *
     */
    public void connectZookeeper(String host) throws Exception {
        int SESSION_TIME_OUT = 2000;
        zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
        countDownLatch.await();
        System.out.println("zookeeper connection success");
    }

    public String createNode(String path, String data) throws Exception {
        return this.zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        return zooKeeper.getChildren(path, false);
    }

    public String getData(String path) throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData(path, false, null);
        if (data == null) {
            return "";
        }

        return new String(data);
    }


    public Stat setData(String path, String data) throws KeeperException, InterruptedException {
        return zooKeeper.setData(path, data.getBytes(), -1);
    }

    public void deleteNode(String path) throws InterruptedException, KeeperException {
        zooKeeper.delete(path, -1);
    }

    /**
     * 获取创建时间
     * @param path node
     */
    public long getCTime(String path) throws KeeperException, InterruptedException{
        Stat stat = zooKeeper.exists(path, false);
        return stat.getCtime();
    }

    /**
     * 获取某个路径下孩子的数量
     * @param path node
     */
    public Integer getChildrenNum(String path) throws KeeperException, InterruptedException{
        return zooKeeper.getChildren(path, false).size();
    }
    /**
     * 关闭连接
     * @throws InterruptedException
     */
    public void closeConnection() throws InterruptedException{
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }


}
