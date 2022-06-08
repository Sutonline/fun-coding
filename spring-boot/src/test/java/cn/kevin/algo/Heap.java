package cn.kevin.algo;

/**
 * 堆的位置:
 * 数组中下标为 i 的节点的左子节点，就是下标为 i∗2 的节点，右子节点就是下标为 i∗2+1 的节点，父节点就是下标为i/2
 *
 * @author yongkang.zhang
 * date 2021/12/5
 */
public class Heap {

	private int[] a; // 数组 从下标1开始存储
	private int n; // 堆可以存储最大的元素个数
	private int count; // 堆中已经存储的元素个数

	public Heap(int capacity) {
		this.n = capacity;
		a = new int[n+1];
		count = 0;
	}

	/**
	 * 新元素的位置在count+1
	 * 与它的父节点比较
	 * 停止条件 index = 1 or parent > element
	 * @param element 元素
	 */
	public void insert(int element) {
		int cur = count + 1;
		a[cur] = element;

		while (cur > 1 && a[cur] > a[cur/2]) {
			swap(cur, cur / 2);
			cur--;
		}
	}

	private void swap(int cur, int parent) {
		int temp = a[cur];
		a[cur] = a[parent];
		a[parent] = temp;
	}
}
