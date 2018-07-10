package cn.kevin.controller;

import cn.kevin.domain.Animal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * These are actually intentionally different exceptions. @ModelAttribute, which is assumed by default if no other
 * annotation is present, goes through data binding and validation, and raises BindException to indicate a failure
 * with binding request properties or validating the resulting values. @RequestBody, on the other hand converts the
 * body of the request via HttpMessageConverter, validates it and raises various conversion related exceptions or a
 * MethodArgumentNotValidexception if validation fails. In most cases a MethodArgumentNotValidException can be handled
 * generically (e.g. via @ExceptionHandler method) while BindException is very often handled individually
 * in each controller method.
 * 另外如果是实体的AssertTrue, 应该使用isXXX这样的格式，以is开头，否则不会生效
 *
 * @author yongkang.zhang
 * created at 09/07/2018
 */
@RestController
public class DemoApiController {

    /**
     * 不valid的会抛出bindException
     */
    @RequestMapping(value = "/ani")
    public String ani(@Valid  Animal animal) {
        return animal.toString();
    }

    @RequestMapping(value = "/aniWithBinding")
    public String aniWithBinding(@Valid Animal animal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Error: " + bindingResult.getAllErrors().get(0).getDefaultMessage();
        }

        return "啦啦啦啦";
    }

    /**
     * 不valid会抛出MethodArgumentNotValidException
     */
    @RequestMapping(value = "/aniRequestBody")
    public String bindWithRequestBody(@Valid @RequestBody Animal animal) {
        return "dududu";
    }

    /**
     * 用@RequestBody也可以捕获到BindResult中
     */
    @RequestMapping(value = "/aniWithRB")
    public String aniWithRB(@Valid @RequestBody Animal animal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "bind error: " + bindingResult.getAllErrors().get(0).getDefaultMessage();
        }

        return "lalall";
    }
}
