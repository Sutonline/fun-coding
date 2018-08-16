package cn.kevin.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yongkang.zhang
 * created at 16/08/2018
 */
@Slf4j
public class OrderEventMain {

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();

        final AtomicInteger atomicInteger = new AtomicInteger();

        OrderEventFactory orderEventFactory = new OrderEventFactory();

        Disruptor<OrderEvent> orderEventDisruptor = new Disruptor<>(orderEventFactory, 8, r -> {
            return new Thread(r, "OrderEventMain-thread-" + atomicInteger.incrementAndGet());
        });

        OrderWorkHandler[] workHandlers = new OrderWorkHandler[10];
        for (int i = 0; i < 10; i++) {
            workHandlers[i] = new OrderWorkHandler();
        }

        ReceiveHandler receiveHandler = new ReceiveHandler();

        DownHandler downHandler = new DownHandler();

        orderEventDisruptor.handleEventsWithWorkerPool(workHandlers)
                .handleEventsWith(receiveHandler)
                .then(downHandler);

        orderEventDisruptor.handleExceptionsFor(receiveHandler).with(new ExceptionHandler<OrderEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, OrderEvent event) {
                log.info("异常是: {}" + ex);
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                log.info("异常是: {}" + ex);
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                log.info("异常是: {}" + ex);
            }
        });

        orderEventDisruptor.start();

        OrderEventProducer producer = new OrderEventProducer(orderEventDisruptor.getRingBuffer());

        producer.produce();

        System.out.println("结束");
    }
}
