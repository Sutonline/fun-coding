package cn.kevin.jdk8.spliterator;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

/**
 * @author yongkang.zhang
 * created at 23/09/2018
 */
public class SpliteratorDemo {

    private static void arraySplitDemo() {
       List<String> list = Arrays.asList("Apple", "Banana", "Orange");

        Spliterator<String> spliterator = list.spliterator();

        spliterator.tryAdvance(System.out::println);

        System.out.println("-- buld traversal");
        spliterator.forEachRemaining(System.out::println);

        System.out.println("--- attempting try advance again");
        System.out.println(spliterator.tryAdvance(System.out::println));
    }


    public static void main(String[] args) {
        arraySplitDemo();
    }

}
