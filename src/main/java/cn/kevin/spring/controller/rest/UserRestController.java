package cn.kevin.spring.controller.rest;

import cn.kevin.spring.domain.User;
import cn.kevin.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * user rest controller
 * created by yongkang.zhang
 * added at 2018/1/5
 */
@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "listAllUser")
    public Flux<User> listAll() {
        return userRepository.listAll();
    }
}
