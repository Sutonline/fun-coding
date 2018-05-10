package cn.kevin.jdk9;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试默认私有接口
 * created by yongkang.zhang
 * added at 2018/1/3
 */
@Slf4j
public class PrivateInterfaceImpl implements PrivateInterfaceDemo {

    @Override
    public String getNameSelf() {
        return this.getClass().getSimpleName();
    }

    public static void main(String[] args) {
        PrivateInterfaceDemo demo = new PrivateInterfaceImpl();
        // 私有interface方法不能调用，只能在default中使用
        log.info("default的结果是: {}", demo.getDefaultName());
        // 自己实现的结果
        log.info("self结果是: {}", demo.getNameSelf());
    }
}
