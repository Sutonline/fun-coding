package cn.kevin.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author yongkang.zhang
 * created at 16/08/2018
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {

    @Override
    public OrderEvent newInstance() {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setId(System.currentTimeMillis());
        return orderEvent;
    }
}
