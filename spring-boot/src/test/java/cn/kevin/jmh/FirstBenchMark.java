package cn.kevin.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * jmh介绍: http://blog.dyngr.com/blog/2016/10/29/introduction-of-jmh/
 * spring boot jmh: https://www.chendayu.com/nonsence/jmh-spring-boot/
 * </pre>
 *
 * @author yongkang.zhang
 * created at 2019-02-20
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class FirstBenchMark {

	@Benchmark
	public int sleepAWhile() {
		try {
			Thread.sleep(500);
		} catch (Exception ignored) {

		}
		return 0;
	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(FirstBenchMark.class.getSimpleName())
				.forks(1)
				.warmupIterations(5)
				.build();

		new Runner(opt).run();
	}
}
