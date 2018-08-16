package cn.kevin.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * @author yongkang.zhang
 * created at 16/08/2018
 */
public class OrderWorkHandler implements WorkHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent event) throws Exception {
        event.setThreadName(Thread.currentThread().getName());
    }
}
