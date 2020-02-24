package cn.kevin.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture的whenComplete是执行任务的线程继续执行结果处理，而不是当前线程
 *
 * @author yongkang.zhang
 * created at 24/02/2020
 */
@Slf4j
public class CompletableFutureTest {

	private ExecutorService executorService = Executors.newFixedThreadPool(12);

	@Test
	public void test_whenComplete() throws InterruptedException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			log.error("thread name: {}", Thread.currentThread().getName());
			return "abc";
		});

		future.whenComplete((result, error) -> {
			try {
				Thread.sleep(5000);
				log.info("嘟啦啦啦, thread name: {}", Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread.sleep(10000);
		log.info("结束了");
	}

	@Test
	public void test_whenCompleteAsync() throws InterruptedException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			log.error("thread name: {}", Thread.currentThread().getName());
			return "abc";
		});

		future.whenCompleteAsync((result, error) -> {
			try {
				Thread.sleep(5000);
				log.info("嘟啦啦啦, thread name: {}", Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread.sleep(10000);
		log.info("结束了");
	}

	@Test
	public void test_executor_whenCompleteAsync() throws InterruptedException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			log.error("thread name: {}", Thread.currentThread().getName());
			return "abc";
		}, executorService);

		future.whenCompleteAsync((result, error) -> {
			try {
				Thread.sleep(5000);
				log.info("嘟啦啦啦, thread name: {}", Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, executorService);

		Thread.sleep(10000);
		log.info("结束了");
	}

	@Test
	public void test_main_whenCompleteAsync() throws InterruptedException {
		CompletableFuture<String> future = CompletableFuture.completedFuture("abc");

		future.whenComplete((result, error) -> {
			try {
				Thread.sleep(5000);
				log.info("嘟啦啦啦, thread name: {}", Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread.sleep(10000);
		log.info("结束了");
	}
}
