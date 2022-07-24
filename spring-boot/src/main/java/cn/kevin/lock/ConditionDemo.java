package cn.kevin.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yongkang.zhang
 * date 2022/7/24
 */
public class ConditionDemo {

    private ReentrantLock reentrantLock = new ReentrantLock(true);

    private Condition emptyCondition = reentrantLock.newCondition();

    private Condition fullCondition = reentrantLock.newCondition();

    private Condition startCondition = reentrantLock.newCondition();

    private Condition endCondition = reentrantLock.newCondition();

    private AtomicInteger count = new AtomicInteger(0);


    public void produce() {
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "produce加锁成功");
            while (true) {
                emptyCondition.await();
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " produce:" + count.incrementAndGet());
                }
                Thread.sleep(5000);
                fullCondition.signal();
                startCondition.signal();
            }
        }  catch (Exception e) {
            System.out.println("异常: " + e.getMessage());
        } finally {
            reentrantLock.unlock();
        }
    }

    public void consume() {
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName() + "consume加锁成功");
            emptyCondition.signal();
            while (true) {
                fullCondition.await();
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " consume:" + count.decrementAndGet());
                }
                Thread.sleep(5000);
                emptyCondition.signal();
                endCondition.signal();
            }
        } catch (Exception e) {
            System.out.println("异常:" + e.getMessage());
        } finally {
            reentrantLock.unlock();
        }
    }

    public void printStart() {
        try {
            reentrantLock.lock();
            while (true) {
                startCondition.await();
                System.out.println(Thread.currentThread().getName() + "start>>>>");
            }
        } catch (Exception e) {
            System.out.println("异常:" + e.getMessage());
        } finally {
            reentrantLock.unlock();
        }
    }

    public void printEnd() {
        try {
            reentrantLock.lock();
            while (true) {
                endCondition.await();
                System.out.println(Thread.currentThread().getName() + "end>>>>");
            }
        } catch (Exception e) {
            System.out.println("异常:" + e.getMessage());
        } finally {
            reentrantLock.unlock();
        }
    }

}
