package cn.kevin.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @author yongkang.zhang
 * created at 18/07/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
