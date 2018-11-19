package com;

import cn.kevin.config.ImportConfiguration;
import cn.kevin.domain.Animal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yongkang.zhang
 * created at 19/11/2018
 */
@Configuration
public class AnimalConfiguration extends ImportConfiguration {

    @Bean
    public Animal animal() {
        Animal animal = new Animal();
        animal.setName("Miao");
        animal.setType("cat");

        return animal;
    }
}
