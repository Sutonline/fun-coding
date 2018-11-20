package cn.kevin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yongkang.zhang
 * created at 20/11/2018
 */
@Component
@Slf4j
public class HelloService {

    public String sayHello() {
        log.info("开始执行sayHello");
        return "llala";
    }
}
