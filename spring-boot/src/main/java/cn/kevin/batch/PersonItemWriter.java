package cn.kevin.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author yongkang.zhang
 * created at 18/07/2018
 */
@Slf4j
public class PersonItemWriter implements ItemWriter<Person> {

    @Override
    public void write(List<? extends Person> list) throws Exception {
        list.forEach(i -> log.info("person: {}", i));
    }
}
