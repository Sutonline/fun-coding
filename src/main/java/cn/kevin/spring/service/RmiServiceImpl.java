package cn.kevin.spring.service;

import org.springframework.stereotype.Service;

@Service
public class RmiServiceImpl implements RmiService {

    @Override
    public String sayHello() {
        return "hello world, i am rmi service implementation from spring";
    }
}
