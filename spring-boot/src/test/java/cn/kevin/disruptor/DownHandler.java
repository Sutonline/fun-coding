package cn.kevin.disruptor;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author yongkang.zhang
 * created at 16/08/2018
 */
@Slf4j
public class DownHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        if (event.getId() > 90) {
            throw new RuntimeException("来个异常试试？");
        }

        event.setDownTime(new Date());
        // log.info("这里做下传处理, event: {}", event);
        log.info("订单下传, threadName: {}, workPoolThreadName: {}", Thread.currentThread().getName(), event.getThreadName());
    }
}
