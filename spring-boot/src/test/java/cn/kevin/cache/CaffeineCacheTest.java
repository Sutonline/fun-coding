package cn.kevin.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author yongkang.zhang
 * created at 2018-12-14
 */
public class CaffeineCacheTest {


	@Test
	public void cacheTest() throws InterruptedException {
		Cache<String, String> cache = Caffeine.newBuilder()
				.maximumSize(1)
				.recordStats()
				.build();

		// 加热缓存
		cache.put("a", "a");
		for (int i = 1; i < 1000000; i++) {
			cache.getIfPresent("a");
		}

		cache.put("b", "b");
		cache.put("c", "c");
		System.out.println(cache.getIfPresent("a"));
		System.out.println(cache.getIfPresent("b"));
		System.out.println(cache.getIfPresent("c"));

		for (int i = 1; i < 100000; i++) {
			cache.getIfPresent("a");
		}


		Thread.sleep(1000);
		System.out.println(cache.getIfPresent("a"));
		System.out.println(cache.getIfPresent("b"));
		System.out.println(cache.getIfPresent("c"));
		CacheStats stats = cache.stats();
		System.out.println(stats);
	}
}
