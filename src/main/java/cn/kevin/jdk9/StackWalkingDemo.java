package cn.kevin.jdk9;

import lombok.extern.slf4j.Slf4j;

/**
 * stack walking
 * created by yongkang.zhang
 * added at 2018/1/3
 */
@Slf4j
public class StackWalkingDemo {

    static void one() {
        two();
    }

    static void two() {
        three();
    }

    static void three() {
        String line = StackWalker.getInstance().walk(stack -> {
            return stack.filter(frame -> frame.getMethodName().contains("one"))
                    .findFirst()
                    .map(frame -> "Line " + frame.getLineNumber())
                    .orElse("Unknown line");
        });
        log.info("line是: {}", line);
    }

    public static void main(String[] args) {
        one();
    }

}
