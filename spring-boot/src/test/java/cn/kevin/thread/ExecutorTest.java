package cn.kevin.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yongkang.zhang
 * created at 2019-01-02
 */
@Slf4j
public class ExecutorTest {

	@Test
	public void test() throws InterruptedException {
		ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
		builder.setNameFormat("exec-%d");
		ExecutorService executor = new ThreadPoolExecutor(2, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));

		for (int i = 0; i < 10; i++) {
			executor.submit(() -> {
				Person person = new Person();
				person.set();
				log.info("输出结果: {}", person.get());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		Thread.sleep(1000 * 60);
		executor.shutdown();
	}


	private static class Person {
		public final static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

		public void set() {
			threadLocal.set(threadLocal.get() + 1);
		}

		public Integer get() {
			return threadLocal.get();
		}
	}
}
