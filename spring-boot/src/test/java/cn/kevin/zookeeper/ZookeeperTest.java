package cn.kevin.zookeeper;

import cn.kevin.ManApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yongkang.zhang
 * created at 13/08/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManApplication.class)
@ActiveProfiles(profiles = {"dev"})
@Slf4j
public class ZookeeperTest {

    @Autowired
    private ZookeeperComponent zookeeperComponent;

    @Test
    public void test() throws Exception {
        zookeeperComponent.createNode("/ronda", "test");
        zookeeperComponent.createNode("/ronda/registry", "1234566");
        String data = zookeeperComponent.getData("/ronda/registry");
        System.out.println(data);
    }
}
