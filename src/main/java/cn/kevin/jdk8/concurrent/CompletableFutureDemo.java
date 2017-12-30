package cn.kevin.jdk8.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFutre 例子
 * created by yongkang.zhang
 * added at 2017/12/29
 */
@Slf4j
public class CompletableFutureDemo {

    private static void run() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.<String>supplyAsync(() -> sendMessage("1"));
        CompletableFuture<String> secondFuture = CompletableFuture.<String>supplyAsync(() -> sendMessage("2"));
        CompletableFuture<String> thirdFuture = CompletableFuture.<String>supplyAsync(() -> sendMessage("3"));
        CompletableFuture<String> forthFuture = CompletableFuture.<String>supplyAsync(() -> sendMessage("4"));


        //CompletableFuture.anyOf(future, secondFuture, thirdFuture, forthFuture).thenAccept(o -> log.info("完成任务"));
        CompletableFuture.allOf(future, secondFuture, thirdFuture, forthFuture).thenAccept(o -> log.info("完成任务"));
        /*CompletableFuture<String> composeFuture = future.thenCompose(s -> {
            log.info("compose的参数值是{}", s);
            return CompletableFuture.<String>supplyAsync(() -> "compose的future" + s);
        });*/

        /*CompletableFuture<String> combineFuture = secondFuture.thenCombine(future, (s, v) -> {
            log.info("这里是combine, {}", s + v);
            return s + v;
        });*/


        // CompletableFuture<Void> acceptFuture = future.thenAccept(s -> log.info(s.concat("第一步的accept")));
        // CompletableFuture<Void> voidCompletableFuture = acceptFuture.thenAccept(aVoid -> log.info("第二步的accept, 返回值已经为void了, 返回值: {}", aVoid));
        // CompletableFuture<Void> acceptSyncFuture = future.thenAcceptAsync(s -> log.info(s.concat("第一步的accept")));
        // acceptSyncFuture.thenAcceptAsync(aVoid -> log.info("第二步的accept, 返回值已经为void了, 返回值: {}", aVoid));
        //CompletableFuture<String> exceptionFuture = future.exceptionally(e -> {
        //    log.info("发生了异常", e);
        //    return e.getMessage();
        //});
        //exceptionFuture.thenAccept(s -> log.info("这是accept".concat(s)));
    }


    private static String sendMessage(String id) {
        log.info("这里是发送信息{}", id);
        return "信息" + id;
    }

    public static void main(String[] args) throws Throwable {
        log.info("启动");
        run();
        log.info("结束");
        Thread.sleep(3 * 1000L);
    }

}
