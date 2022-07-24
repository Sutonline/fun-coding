package cn.kevin.lock;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yongkang.zhang
 * date 2022/7/24
 */
public class SynchronizedDemo {

    private AtomicInteger count = new AtomicInteger(0);

    @SneakyThrows
    public synchronized void produce() {
        System.out.println(Thread.currentThread().getName() + "produce加锁成功");
        Thread.sleep(3000);
        while (true) {
            wait();
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " consume:" + count.incrementAndGet());
            }
            Thread.sleep(5000);
            notify();
        }
    }

    @SneakyThrows
    public synchronized void consume() {
        System.out.println(Thread.currentThread().getName() + "consume加锁成功");
        notify();
        while (true) {
            wait();
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " produce:" + count.decrementAndGet());
            }
            Thread.sleep(5000);
            notify();
        }
    }
}
