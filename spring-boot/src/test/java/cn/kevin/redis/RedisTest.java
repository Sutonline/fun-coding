package cn.kevin.redis;

import cn.kevin.ManApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yongkang.zhang
 * created at 14/08/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManApplication.class)
@ActiveProfiles(profiles = {"dev"})
@Slf4j
public class RedisTest {

    @Autowired
    private RedisComponent redisComponent;

    @Test
    public void test() {
        redisComponent.setValue("lalal", "kukikuikukiki");
        System.out.println(redisComponent.getData("lalal"));
    }
}
