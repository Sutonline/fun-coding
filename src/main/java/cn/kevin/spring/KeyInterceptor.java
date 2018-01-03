package cn.kevin.spring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * key注解对应的interceptor
 * created by yongkang.zhang
 * added at 2018/1/2
 */
@Aspect
@Component
@Slf4j
public class KeyInterceptor {

    @Around(value = "@annotation(cn.kevin.spring.KeyAnnotation)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        KeyAnnotation keyAnnotation = method.getAnnotation(KeyAnnotation.class);
        if(keyAnnotation == null) {
            return joinPoint.proceed();
        }

        String key = String.valueOf(parseSpelKey(
                methodSignature.getParameterNames(), joinPoint.getArgs(), keyAnnotation.key()));

        // key的业务逻辑判断
        if (key.equals("A")) {
            log.info("捕获到了A");
        }

        return null;
    }

    private Object parseSpelKey(String[] parameterNames, Object[] args, String key) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context);
    }

}
