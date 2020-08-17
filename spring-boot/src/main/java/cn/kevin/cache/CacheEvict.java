package cn.kevin.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 验证guava cache 和 caffeine cache的evict策略机制
 * @author yongkang.zhang
 * @date 2020/8/16
 */
public class CacheEvict {

	private static Cache<String, String> guavaCache = CacheBuilder.<String, String>newBuilder()
			.maximumSize(3)
			.expireAfterWrite(3, TimeUnit.SECONDS)
			.build();


	private static void guavaEvict() throws InterruptedException {
		// 放入cache 3秒后再访问
		guavaCache.put("test", "test");
		System.out.println(guavaCache.getIfPresent("test"));

		Thread.sleep(1000 * 2);
		System.out.println("2s后:" + guavaCache.getIfPresent("test"));

		Thread.sleep(1000 * 1);
		System.out.println("3s后:" + guavaCache.getIfPresent("test"));
		System.out.println("3s后size:" + guavaCache.size());

		Thread.sleep(1000 * 3);
		System.out.println("6s后:" + guavaCache.getIfPresent("test"));
		System.out.println("6s后size:" + guavaCache.size());

		// cleanup
		guavaCache.cleanUp();
		System.out.println("cleanup后:" + guavaCache.getIfPresent("test"));

		guavaCache.put("test", "test");
		guavaCache.put("test1", "test1");
		guavaCache.put("test2", "test2");
		guavaCache.put("test3", "test3");
		System.out.println("超过最大数量test: " + guavaCache.getIfPresent("test"));
		System.out.println("超过最大数量test1: " + guavaCache.getIfPresent("test1"));
		System.out.println("超过最大数量test2: " + guavaCache.getIfPresent("test2"));
		System.out.println("超过最大数量test3: " + guavaCache.getIfPresent("test3"));
		System.out.println("超过最大数量size:" + guavaCache.size());
	}

	public static void main(String[] args) throws InterruptedException {
		guavaEvict();
	}

}
