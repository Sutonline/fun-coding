package cn.kevin.jdk8.lambda;

import java.util.function.Consumer;

/**
 * @author yongkang.zhang
 * created at 2019-04-03
 */
public class LambdaTest {

	public static void main(String[] args) {
		tryCatchWrap(() -> {
			String s = new String("dd");
			String s1 = new String("dd");
			String s2 = new String("dd");
			String s3 = new String("dd");
			test();
			String s4 = new String("dd");
			String s5 = new String("dd");
		}, e -> {
			throw new RuntimeException(e);
		});
	}

	private static void tryCatchWrap(Runnable runnable, Consumer<Exception> handler) {
		try {
			runnable.run();
		} catch (Exception e) {
			handler.accept(e);
		}
	}

	private static void test() {
		int i = 0;
		int j = 5 / i;
		System.out.println(j);
	}

}
