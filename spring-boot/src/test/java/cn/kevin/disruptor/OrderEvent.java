package cn.kevin.disruptor;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 订单下单事件
 * @author yongkang.zhang
 * created at 16/08/2018
 */
@Data
public class OrderEvent {

    // 流水id
    private Long id;

    // 客户账号
    private String customPin;

    // 商品
    private List<String> goods;

    // 接受时间
    private Date receiveTime;

    private Date downTime;

    private Date storeTime;

    private String threadName;
}
