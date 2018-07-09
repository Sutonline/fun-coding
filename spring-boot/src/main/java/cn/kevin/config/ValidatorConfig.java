package cn.kevin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author yongkang.zhang
 * created at 09/07/2018
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
