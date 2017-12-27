package cn.kevin.tree.six;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

/**
 * 二叉搜索树
 * created by yongkang.zhang
 * added at 2017/12/21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BinarySearchTree<K extends Comparable, V> {

    private K key;
    private HashSet<V> set = new HashSet<>();
    private BinarySearchTree<K, V> left;
    private BinarySearchTree<K, V> right;

    public BinarySearchTree(K key, V value, BinarySearchTree<K, V> left, BinarySearchTree<K, V> right) {
        this.key = key;
        this.set.add(value);
        this.left = left;
        this.right = right;
    }

    public BinarySearchTree<K, V> add(K key, V value, BinarySearchTree<K, V> tree) {
        if (tree == null) {
            tree = new BinarySearchTree<K, V>(key, value, null, null);
        }

        // 左子树
        if (key.compareTo(tree.key) < 0) {
            tree.left = add(key, value, tree.left);
        }

        // 右子树
        if (key.compareTo(tree.key) > 0) {
            tree.right = add(key, value, tree.right);
        }

        // 如果相等，就对节点附加值加1
        if (key.compareTo(tree.key) == 0) {
            tree.set.add(value);
        }

        return tree;
    }

    public HashSet<V> searchRange(K min, K max) {
        HashSet<V> hashSet = new HashSet<>();
        return hashSet;
    }


    private HashSet<V> searchRange(K min, K max, HashSet<V> hashSet, BinarySearchTree<K, V> tree) {
        if (tree == null) {
            return hashSet;
        }

        // 遍历左子树(寻找下界)
        if (min.compareTo(tree.key) < 0) {
            searchRange(min, max, hashSet, tree.left);
        }

        // 当前节点是否在选定范围之内
        if (min.compareTo(tree.key) <= 0 && max.compareTo(tree.key) >= 0) {
            tree.set.forEach(hashSet::add);
        }

        // 遍历右子树(两种情况: 1: 找min的下限 2: 必须在max范围之内
        if (min.compareTo(tree.key) > 0 || max.compareTo(tree.key) > 0) {
            searchRange(min, max, hashSet, tree.right);
        }

        return hashSet;
    }


    public BinarySearchTree<K, V> remove(K key, V value, BinarySearchTree<K, V> tree) {
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
            if (tree.set.size() > 1) {
                tree.set.remove(value);
            } else {
                if (tree.left != null && tree.right != null) {
                    // 根据二叉树的中序遍历，需要找到"有子树"的最小节点
                    tree.key = findMin(tree.right).key;
                } else {
                    tree = tree.left == null ? tree.right : tree.left;
                }
            }
        }

        return tree;
    }

    /**
     * todo implemented
     * @param tree
     * @return
     */
    private BinarySearchTree<K, V> findMin(BinarySearchTree<K, V>  tree) {
        return null;
    }
}
