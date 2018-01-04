package cn.kevin.jdk9;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;

/**
 * react stream 很像RxJava2
 * created by yongkang.zhang
 * added at 2018/1/3
 */
@Slf4j
public class ReactiveStreams {

    public static void main(String[] args) {
        // 创建订阅者
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                log.info("订阅成功, {}", subscription);
            }

            @Override
            public void onNext(String item) {
                log.info("收到的内容是: {}", item);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        };


        Flow.Publisher<String> publisher = new Flow.Publisher<String>() {
            @Override
            public void subscribe(Flow.Subscriber<? super String> subscriber) {
                subscriber.onNext("我不是啦啦啦啦啦");
            }
        };

        publisher.subscribe(subscriber);
    }

}
