package cn.kevin.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.TreeSet;

/**
 * @author yongkang.zhang
 * date 2022/7/9
 */
@Slf4j
public class LangUtils {

    @SneakyThrows
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(3);
        treeSet.add(2);
        treeSet.add(4);
        treeSet.add(1);
        treeSet.add(5);

        for (Integer integer : treeSet) {
            System.out.println("=====>" + integer);
        }
    }

}
