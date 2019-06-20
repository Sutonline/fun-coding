package cn.kevin.annotation;

import cn.kevin.ManApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author yongkang.zhang
 * created at 2019-06-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManApplication.class)
@ActiveProfiles(profiles = {"dev"})
@Slf4j
public class ResourceTest {


	/*@Resource
	private cn.kevin.annotation.sub.HelloService helloServiceImpl;*/
	@Resource
	private ResourceService resourceService;

	@Test
	public void test() {
		// welcomeServiceImpl.sayHell0();
	}
}
