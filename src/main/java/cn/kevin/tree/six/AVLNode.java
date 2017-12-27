package cn.kevin.tree.six;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

/**
 * 由搜索二叉树引过来的, 再查找的时候不是严格的O(logN)，导致真实场景中没有用武之地，谁也不愿意有O(N)的情况发生
 * 为了追求严格的O(logN)，产生的结果是平衡二叉树(AVL)
 * 定义: 父节点的左子树和右子树的高度之差不能大于1
 * visit https://zh.wikipedia.org/wiki/AVL%E6%A0%91 for detail
 *
 * created by yongkang.zhang
 * added at 2017/12/21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AVLNode<K extends Comparable<K>, V> {

    public K key;
    // 高度信息
    private int height;
    // 节点中的附加值
    private HashSet<V> attach = new HashSet<>();
    // 左节点
    private AVLNode<K, V> left;
    // 右节点
    private AVLNode<K, V> right;

    public AVLNode(K key, V value, AVLNode<K, V> left, AVLNode<K, V> right) {
        // KV键值对
        this.key = key;
        this.attach.add(value);
        this.left = left;
        this.right = right;
    }

    /**
     * 左左旋转
     * 1. 将ROOT的左节点当成TOP节点
     * 2. 将TOP的右子树挂在ROOT的左子树上
     * 3. 将ROOT作为TOP的右子树
     *
     * @param node 节点
     * @return 旋转后的结果
     */
    public AVLNode<K, V> rotateLL(AVLNode<K, V> node) {
        // 将左节点作为TOP节点
        AVLNode<K, V> top = node.left;

        // 将top的右子树挂在node的左子树上
        node.left = top.right;

        // 当node节点作为top的右子树
        top.right = node;

        // 计算当前两个节点的高度
        node.height = Math.max(node.left.height, node.right.height) + 1;
        top.height = Math.max(top.left.height, top.right.height) + 1;

        return top;
    }

    /**
     * 右右旋转
     * 1. 将ROOT的右节点作为TOP节点
     * 2. 将TOP节点的左子树挂在ROOT的右子树上
     * 3. 将ROOT节点作为TOP的左子树
     * @param node 最初的root节点
     * @return 旋转后的root
     */
    public AVLNode<K, V> rotateRR(AVLNode<K, V> node) {
        // 将ROOT的右子点作为TOP节点
        AVLNode<K, V> top = node.right;

        // 将TOP节点的左子树挂在ROOT的右子树上
        node.right = top.left;

        // 将node节点作为TOP的左节点
        top.left = node;

        // 因为旋转，重新计算高度
        node.height = Math.max(node.left.height, node.right.height) + 1;
        top.height = Math.max(top.left.height, top.right.height) + 1;

        return top;
    }

    public AVLNode<K, V> rotateLR(AVLNode<K, V> node) {
        // 先进行RR旋转
        node.left = rotateRR(node.left);

        // 在进行LL旋转
        return rotateLL(node);
    }

    public AVLNode<K, V> rotateRL(AVLNode<K, V> node) {
        // 先进行LL旋转
        node.right = rotateLL(node.right);

        // 再向右旋转
        return rotateRR(node);
    }

    public AVLNode<K, V> add(K key, V value, AVLNode<K, V> tree) {
        if (tree == null) {
            tree = new AVLNode<>(key, value, null, null);
        }

        // 左子树
        if (key.compareTo(tree.key) < 0) {
            tree.left = add(key, value, tree.left);

            // 如果说相差等于2就说明这棵树需要旋转了
            if ((tree.left.height - tree.right.height) == 2) {
                // 说明此事是左左旋转
                if (key.compareTo(tree.left.key) < 0) {
                    tree = rotateLL(tree);
                } else { // 属于左右旋转
                    tree = rotateLR(tree);
                }
            }
        }

        // 右子树
        if (key.compareTo(tree.key) > 0) {
            tree.right = add(key, value, tree.right);
            if ((tree.right.height - tree.left.height) == 2) {
                // 此时是右右旋转
                if (key.compareTo(tree.right.key) > 0) {
                    tree = rotateRR(tree);
                } else { // 否则是右左旋转
                    tree = rotateRL(tree);
                }
            }
        }

        // 两个相等
        if (key.compareTo(tree.key) == 0) {
            tree.attach.add(value);
        }

        // 计算高度
        tree.height = Math.max(tree.left.height, tree.right.height) + 1;

        return tree;
    }

    public AVLNode<K, V> remove(K key, V value, AVLNode<K, V> tree) {
        if (tree == null) {
            return null;
        }

        // 左子树
        if (key.compareTo(tree.key) < 0) {
            tree.left = remove(key, value, tree.left);

            // 如果相差2，就旋转
            if (tree.left.height - tree.right.height == 2) {
                if (key.compareTo(tree.left.key) < 0) {
                    tree = rotateLL(tree); // 左左旋转
                } else {
                    tree = rotateLR(tree); // 左右旋转
                }
            }
        }

        if (key.compareTo(tree.key) > 0) {
            tree.right = remove(key, value, tree.right);

            if (tree.right.height - tree.left.height == 2) {
                if (key.compareTo(tree.right.key) > 0) {
                    tree = rotateRR(tree);
                } else {
                    tree = rotateRL(tree);
                }
            }
        }

        // 相等的情况
        if (key.compareTo(tree.key) == 0) {
            if (tree.attach.size() > 1) {
                tree.attach.remove(value);
            } else {
                if (tree.left != null && tree.right != null) {
                    tree.key = findMin(tree.right).key;
                    // 删除右子树的指定元素
                    tree.right = remove(tree.key, value, tree.right);
                } else {
                    tree = tree.left == null ? tree.right : tree.right;

                    // 如果删除的是叶节点直接返回
                    if (tree == null) {
                        return null;
                    }
                }
            }
        }

        // 计算高度
        tree.height = Math.max(tree.left.height, tree.right.height) + 1;

        return tree;
    }

    /**
     * todo implemented
     * @param tree 树
     * @return 返回的最小的节点
     */
    private AVLNode<K, V> findMin(AVLNode<K, V> tree) {
        return null;
    }


}
