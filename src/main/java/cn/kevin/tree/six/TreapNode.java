package cn.kevin.tree.six;

import lombok.Data;

import java.util.HashSet;
import java.util.Random;

/**
 * treap树算是一种简单的优化策略，是树和堆的合体。
 * 原理是在书中维护一个优先级，优先级采用随机数的方法，但是优先级必须满足根堆的性质，当然是"大根堆"或者"小根堆"都无所谓
 * created by yongkang.zhang
 * added at 2017/12/28
 */
@Data
public class TreapNode<K extends Comparable<K>, V> {

    // 节点元素
    private K key;

    // 优先级
    private int priority;

    // 节点中的附加值
    private HashSet<V> attach = new HashSet<>();

    // 左节点
    private TreapNode<K, V> left;

    // 右节点
    private TreapNode<K, V> right;

    public TreapNode() {
    }

    public TreapNode(K key, V value, TreapNode<K, V> left, TreapNode<K, V> right) {
        this.key = key;
        this.priority = new Random(System.currentTimeMillis()).nextInt();
        this.attach.add(value);
        this.left = left;
        this.right = right;
    }

    private TreapNode<K, V> add(K key, V value, TreapNode<K, V> tree) {
        if (tree == null) {
            tree = new TreapNode<K, V>(key, value, null, null);
        }

        // 左子树
        if (key.compareTo(tree.key) < 0) {
            tree.left = add(key, value, tree.left);
            // 根据小根堆性质，需要"左左情况旋转"
            if (tree.left.priority < tree.priority) {
                //tree = rotateLL(tree);
            }
        }

        // 右子树
        if (key.compareTo(tree.key) > 0) {
            tree.right = add(key, value, tree.right);

            // 根据小根堆性质，需要"右右情况旋转"
            if (tree.right.priority < tree.priority) {
                // tree = rotateRR(tree)
            }
        }

        // 如果是同样的key
        if (key.compareTo(tree.key) == 0) {
            tree.attach.add(value);
        }

        return tree;
    }

    public TreapNode<K, V> remove(K key, V value, TreapNode<K, V> tree) {
        if (tree == null) {
            return null;
        }

        // 左子树
        if (key.compareTo(tree.key) < 0) {
            tree.left = remove(key, value, tree.left);
        }

        // 右子树
        if (key.compareTo(tree.key) > 0) {
            tree.right = remove(key, value, tree.right);
        }

        // 相等的情况
        if (key.compareTo(tree.key) == 0) {
            // 判断attach是否有多余的值
            if (tree.attach.size() > 1) {
                tree.attach.remove(value);
            } else {
                //有两个孩子的情况
                if (tree.left != null && tree.right != null) {
                    //如果左孩子的优先级低就需要“左旋”
                    if (tree.left.priority < tree.right.priority) {
                        // tree = RotateLL(tree);
                    } else {
                        //否则“右旋”
                        // tree = RotateRR(tree);
                    }

                    //继续旋转
                    tree = remove(key, value, tree);
                } else {
                    //如果旋转后已经变成了叶子节点则直接删除
                    if (tree == null)
                        return null;

                    //最后就是单支树
                    tree = tree.left == null ? tree.right : tree.left;
                }
            }
        }

        return tree;
    }
}
}
