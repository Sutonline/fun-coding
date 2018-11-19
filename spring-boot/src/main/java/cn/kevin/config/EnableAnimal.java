package cn.kevin.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AnimalConfigurationSelector.class)
public @interface EnableAnimal {

    AdviceMode mode() default AdviceMode.PROXY;
}
