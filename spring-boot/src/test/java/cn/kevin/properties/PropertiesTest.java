package cn.kevin.properties;

import org.junit.Test;

import java.util.Map;
import java.util.Properties;

/**
 * @author yongkang.zhang
 * created at 2019-07-16
 */
public class PropertiesTest {

	@Test
	public void systemProperties() {
		Map<String, String> envMap = System.getenv();
		System.out.println(envMap.toString());
		System.out.println(System.getenv("USER"));
		Properties properties = System.getProperties();
		System.out.println(properties.toString());
		System.out.println(System.getProperty("user.name"));
	}
}
