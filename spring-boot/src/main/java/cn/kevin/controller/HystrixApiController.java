package cn.kevin.controller;

import cn.kevin.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yongkang.zhang
 * created at 21/08/2018
 */
@RestController
public class HystrixApiController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping(value = "/greet/{username}")
    public String greeting(@PathVariable("username") String username) {
        return greetingService.getGreeting(username);
    }
}
