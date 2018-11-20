package cn.kevin;

import cn.kevin.config.EnableAnimal;
import cn.kevin.mongo.Customer;
import cn.kevin.mongo.CustomerRepository;
import cn.kevin.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy
//@ImportResource("spring-job-config.xml.bak")
@Slf4j
public class ManApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HelloService helloService;

    public static void main(String[] args) {
        SpringApplication.run(ManApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("返回结果是: " + helloService.sayHello());
    }
}
