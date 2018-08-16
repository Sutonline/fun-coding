package cn.kevin.disruptor;


import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author yongkang.zhang
 * created at 16/08/2018
 */
@Slf4j
public class ReceiveHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent orderEvent, long sequence, boolean isEnd) throws Exception {
        if (orderEvent.getId() > 90) {
            throw new RuntimeException("来个异常试试？");
        }
        orderEvent.setReceiveTime(new Date());
        // log.info("这里做一下数据处理, 收到的订单是: {}", orderEvent);
        // log.info("receiveHandler, sequence: {}, isEnd: {}", sequence, isEnd);
        log.info("接受处理, threadName: {}, workPoolThreadName: {}", Thread.currentThread().getName(), orderEvent.getThreadName());
    }
}
