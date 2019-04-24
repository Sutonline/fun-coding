package cn.kevin.aop;

import cn.kevin.ManApplication;
import cn.kevin.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yongkang.zhang
 * created at 2019-04-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManApplication.class)
@ActiveProfiles(profiles = {"dev"})
@Slf4j
public class JPTest {

	@Autowired
	private HelloService helloService;

	@Test
	public void test() {
		helloService.sayHello();
	}
}
