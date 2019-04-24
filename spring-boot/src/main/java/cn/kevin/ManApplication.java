package cn.kevin;

import cn.kevin.domain.Student;
import cn.kevin.mybatis.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
//@ImportResource("spring-job-config.xml.bak")
@MapperScan("cn.kevin.mybatis")
@EnableAspectJAutoProxy
@Slf4j
public class ManApplication implements CommandLineRunner {

	/*@Autowired
	private CustomerRepository customerRepository;*/
	@Resource
	private StudentMapper mapper;


	public static void main(String[] args) {
		SpringApplication.run(ManApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
