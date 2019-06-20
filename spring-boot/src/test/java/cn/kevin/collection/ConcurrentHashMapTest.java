package cn.kevin.collection;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap和Hashtable的key,value都不允许null元素
 *
 * @author yongkang.zhang
 * created at 2019-05-23
 */
public class ConcurrentHashMapTest {

	@Test
	public void mapTest() {
		Map<String, String> map = new ConcurrentHashMap<>();
		try {
			map.put(null, "我是value");
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			map.put("key", null);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void hashTableTest() {
		Hashtable<String, String> hashtable = new Hashtable<>();
		try {
			hashtable.put(null, "value");
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			hashtable.put("key", null);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void name() {
		BigDecimal bigDecimal = new BigDecimal(2.344);
		int i = bigDecimal.intValue();
		System.out.println(i);
		int ij = bigDecimal.intValueExact();
		System.out.println(ij);
	}
}
