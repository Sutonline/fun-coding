package cn.kevin.algo;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

/**
 * @author yongkang.zhang
 * date 2021/12/11
 */
public class Bm {

	/**
	 * 一共有256种字符
	 */
	private static final int SIZE = 256;

	/**
	 *
	 * @param a 主串
	 * @param n 主串的长度
	 * @param b 模式串
	 * @param m 模式串的长度
	 * @return
	 */
	public int bm(char[] a, int n, char[]b, int m) {
		// 构建bad character数组 记录模式串中每个字符最后出现的位置
		int[] bc = new int[SIZE];
		generateBC(b, m, bc);
		// 构建好后缀相关数组
		int[] suffix = new int[m];
		boolean[] prefix = new boolean[m];
		generateGS(b, m, suffix, prefix);

		// i表示主串与模式串对齐的第一个字符
		int i = 0;
		while (i <= n-m) {
			// j代表坏字符串在模式串中的下标
			int j;
			// 模式串从后向前匹配
			for (j = m - 1; j >= 0; --j) {
				// 遇见不匹配的就退出循环
				if (a[i+j] != b[j]) break;
			}
			// j小于0代表上面的循环结束了，字符串已经匹配成功，所以就返回第一个匹配字符的位置
			if (j < 0) {
				return i;
			}
			// 坏字符移动的位置
			int x = j - bc[a[i+j]];
			int y = 0;
			// 代表有好后缀
			if (j < m - 1) {
				y = moveByGS(j, m, suffix, prefix);
			}
			i = i + Math.max(x, y);
		}

		return -1;
	}

	/**
	 * 根据好后缀进行移动
	 * @param j 坏字符对应的下标
	 * @param m m代表模式串长度
	 * @param suffix 好后缀数组
	 * @param prefix 前缀数组
	 * @return 移动位置
	 */
	private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
		// 好后缀长度
		int k = m - 1 - j;
		// 如果有全匹配的好后缀
		if (suffix[k] != -1) {
			return j - suffix[k] + 1;
		}
		// 寻找是否有prefix前缀
		for (int r = j + 2; r < m - 1; ++r) {
			if (prefix[m-r] == true) {
				return r;
			}
		}

		// 不满足上述两个情况就直接全移动
		return m;
	}


	/**
	 * BC 代表的是bad character
	 * @param b 是模式串
	 * @param m 模式串长度
	 */
	private void generateBC(char[] b, int m, int[] bc) {
		for (int i = 0; i < SIZE; ++i) {
			bc[i] = -1;
		}

		for (int i = 0; i < m; ++i) {
			// 计算ASCII值
			int ascii = (int)b[i];
			bc[ascii] = i;
		}
	}

	/**
	 * GS = good suffix
	 * @param b 模式串
	 * @param m 模式串长度
	 * @param suffix 后缀数组
	 * @param prefix 前缀数组
	 */
	private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
		// 初始化
		for (int i = 0; i < m; ++i) {
			suffix[i] = -1;
			prefix[i] = false;
		}

		// 对模式串进行依次进行循环
		for (int i = 0; i < m - 1; ++i) {
			int j = i;
			// 公共后缀子串长度
			int k = 0;
			// 与b[0, m - 1]求公共后缀子串
			// b[j] = 前面的字符串
			// b[m - 1 -k] = 后面的字符串
			// 两者都向左移动，寻找找最大的长度
			while (j >= 0 && b[j] == b[m - 1 - k]) {
				--j;
				++k;
				suffix[k] = j + 1;
			}

			// 如果公共字符串子串已经到了最开始的位置，那么就代表是开头
			if (j == -1) {
				prefix[k] = true;
			}
		}
	}
}
