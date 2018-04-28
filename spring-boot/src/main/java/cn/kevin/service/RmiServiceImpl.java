package cn.kevin.service;

import org.springframework.stereotype.Component;

@Component(value = "rmiServiceImpl")
public class RmiServiceImpl implements RmiService {

    @Override
    public String sayHello() {
        return "hello world, i am from spring rmi service";
    }
}
