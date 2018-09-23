package cn.kevin;

import cn.kevin.mongo.Customer;
import cn.kevin.mongo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@ImportResource("spring-job-config.xml.bak")
public class ManApplication {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(ManApplication.class, args);
    }

    /*@Override*/
    public void run(String... args) throws Exception {
        customerRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Smith"));

        System.out.println("Customers found with findAll()");
        System.out.println("------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }

        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(customerRepository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : customerRepository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}
