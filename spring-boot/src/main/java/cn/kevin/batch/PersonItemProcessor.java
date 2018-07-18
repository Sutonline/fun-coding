package cn.kevin.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author yongkang.zhang
 * created at 18/07/2018
 */
@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {
        return new Person(person.getLastName().toUpperCase(), person.getFirstName().toUpperCase());
    }
}
