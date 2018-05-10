package cn.kevin.rmi;

import cn.kevin.service.RmiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RmiClientTest {

    @Resource(name = "helloService")
    private RmiService helloService;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void invoke() {
        Assert.isTrue(helloService != null, "rmi service cannot be null");
        System.out.println(helloService.sayHello());
    }
}
