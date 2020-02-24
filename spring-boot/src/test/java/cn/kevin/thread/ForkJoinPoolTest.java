package cn.kevin.thread;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @author yongkang.zhang
 * created at 01/02/2020
 */
public class ForkJoinPoolTest {

	@Test
	public void forkJoinTest() {
		ForkJoinPool forkJoinPool = new ForkJoinPool(5);
		IntStream.range(0, 1000).parallel().forEach(i -> {
			try {
				forkJoinPool.submit(() -> IntStream.range(0, 10000000).parallel().forEach(c -> {
					System.out.println(i + "s----" + forkJoinPool.toString() + "---" + forkJoinPool.getPoolSize() + "---" + forkJoinPool.getRunningThreadCount());
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(i + "e----" + forkJoinPool.toString() + "---" + Thread.currentThread().getName());
				}));
				System.out.println("submit结束");
			} catch (Exception e) {
				System.out.println(e.toString() + i);
			}
		});
	}
}
