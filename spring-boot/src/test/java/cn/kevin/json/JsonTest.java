package cn.kevin.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.util.*;

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

	@Test
	public void jsonGeneric() {
		Map<String, String> data = new HashMap<>();
		data.put("age", "3");
		data.put("name", "张三");
		data.put("aaName", "李四");
		data.put("birthday", "2019-06-11 20:59:58");

		Person result = new Person();
		parse(data, result);
//		modify(result);

		System.out.println(result);
	}

	@Test
	public void testJsonArray() {
		List<Object> list = new ArrayList<>();
		list.add("abc");
		Person person = new Person();
		person.setAaName("张三");
		list.add(JSON.toJSON(person));
		JSONArray array = new JSONArray(list);
		System.out.println(JSON.toJSONString(array, SerializerFeature.WriteClassName));
	}

	private <T> void parse(Map<String, String> data, T result) {
		String json = JSON.toJSONString(data);

		result = (T) JSON.parseObject(json, result.getClass());

		System.out.println(result);
	}

	private void modify(Person person) {
		person = new Person();
		person.setName("王五");
	}
}
