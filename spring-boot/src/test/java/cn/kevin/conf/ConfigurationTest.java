package cn.kevin.conf;

import cn.kevin.ManApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yongkang.zhang
 * created at 14/07/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManApplication.class)
@ActiveProfiles(profiles = {"dev"})
@Slf4j
public class ConfigurationTest {

    @Value(value = "${user.alias}")
    private String value;
    @Value(value = "${user.password}")
    private String password;

    @Test
    public void test() {
        System.out.println(value  + " " + password);
    }
}
