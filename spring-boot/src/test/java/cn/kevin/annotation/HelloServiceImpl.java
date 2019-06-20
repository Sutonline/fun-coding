package cn.kevin.annotation;

import org.springframework.stereotype.Service;

/**
 * @author yongkang.zhang
 * created at 2019-06-20
 */
@Service("helloServiceImpl")
public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHell0() {
		System.out.println("23434");
	}
}
