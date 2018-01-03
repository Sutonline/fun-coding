package cn.kevin.spring;

import java.lang.annotation.*;

/**
 * 用来做key的annotation
 * created by yongkang.zhang
 * added at 2018/1/2
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KeyAnnotation {

    String key();
}
