package cn.kevin.json;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Date;

/**
 * @author yongkang.zhang
 * created at 2018-12-27
 */
public class JsonTest {

	@Test
	public void jsonTest() {
		Person person = new Person();
		person.setAge(3);
		person.setName("张三");
		person.setAaName("李四");
		person.setBirthday(new Date());

		System.out.println(JSON.toJSONString(person));
	}
}
