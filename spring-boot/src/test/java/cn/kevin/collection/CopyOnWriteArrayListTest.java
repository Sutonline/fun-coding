package cn.kevin.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 特性:
 * 1. 线程安全
 * 2. 每次更新都会copy生成一个新的list
 * 3. 生成iterator都是基于当时list的snapshot，不会受到list本身更新的影响，从而不会抛出ConcurrentModificationException
 * 4. iterator不支持element-changing,add, set, add等操作
 *
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/CopyOnWriteArrayList.html">javadoc链接</a>
 *
 * @author yongkang.zhang
 * created at 2019-05-15
 */
public class CopyOnWriteArrayListTest {

	/**
	 * throws java.lang.UnsupportedOperationException
	 */
	@Test
	public void testItRemove() {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			iterator.remove();
		}
	}

	/**
	 * 正常执行
	 */
	@Test
	public void testListRemove() {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
			list.remove(next);
		}
		System.out.println(list);
	}

	/**
	 * 正常执行
	 */
	@Test
	public void testListAdd() {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
			list.add(next);
		}
		System.out.println(list);
	}

	/**
	 * 不论是list.add还是list.remove都会抛出java.util.ConcurrentModificationException
	 * 使用iterator.remove正常移除
	 */
	@Test
	public void normalAdd() {
		List<String> list = new ArrayList<>();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
			iterator.remove();
		}
		System.out.println(list);
	}
}
