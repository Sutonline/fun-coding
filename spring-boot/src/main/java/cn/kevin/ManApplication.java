package cn.kevin;

import cn.kevin.config.EnableAnimal;
import cn.kevin.domain.User;
import cn.kevin.mongo.Customer;
import cn.kevin.mongo.CustomerRepository;
import cn.kevin.mybatis.UserMapper;
import cn.kevin.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableAspectJAutoProxy
//@ImportResource("spring-job-config.xml.bak")
@Slf4j
public class ManApplication implements CommandLineRunner {

    /*@Autowired
    private CustomerRepository customerRepository;*/
    @Autowired
    private UserMapper userMapper;


    public static void main(String[] args) {
        SpringApplication.run(ManApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("韩伟伟");
        user.setSex(2);
        userMapper.insert(user);

        User liLei = new User();
        liLei.setId(2L);
        liLei.setName("李磊");
        liLei.setSex(1);
        userMapper.insert(liLei);

        List<User> users = userMapper.selectAll();
        log.info("查询结果是: {}", users);
    }
}
