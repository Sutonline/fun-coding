package cn.kevin.jdk9;

import java.util.List;
import java.util.Map;

/**
 * 集合类
 * created by yongkang.zhang
 * added at 2018/1/3
 */
public class CollectionDemo {

    public static void main(String[] args) {
        List<String> list = List.of("a", "b", "c");
        Map<String, Integer> map = Map.of("one", 1, "two", 2, "three", 3);
    }
}
