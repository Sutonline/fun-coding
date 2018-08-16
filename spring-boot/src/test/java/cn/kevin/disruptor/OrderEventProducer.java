package cn.kevin.disruptor;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yongkang.zhang
 * created at 16/08/2018
 */
public class OrderEventProducer {

    private final RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void produce() {
        mockOrderEventList().forEach(this::produce);
    }

    private void produce(OrderEvent orderEvent) {
        this.ringBuffer.publishEvent(translator(), orderEvent);
    }

    private static EventTranslatorOneArg<OrderEvent, OrderEvent> translator() {
        return (destEvent, sequence, srcEvent) -> {
            destEvent.setId(srcEvent.getId());
            destEvent.setGoods(srcEvent.getGoods());
            destEvent.setCustomPin(srcEvent.getCustomPin());
        };
    }

    private List<OrderEvent> mockOrderEventList() {
        List<OrderEvent> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setId((long) i);
            orderEvent.setCustomPin("ceshi" + i);
            orderEvent.setGoods(Lists.newArrayList("good" + i));
            list.add(orderEvent);
        }

        return list;
    }
}
