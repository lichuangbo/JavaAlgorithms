package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 222. 完全二叉树的节点个数
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * <p>
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * 示例1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/25 17:03
 * @Description: 描述:
 */
public class CountNodes {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 时间O(n)
     * 层序遍历统计
     * @param root
     * @return
     */
    public int countNodes1(TreeNode root) {
        int count = 0;
        if (root == null) {
            return count;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            count += size;
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }
            }
        }
        return count;
    }

    /**
     * 时间O(logn * logn)
     * 分别统计左子树和右子树的高度，left和right
     *       1
     *     /  \
     *    2    3
     * 如果left==right，说明左子树是满二叉树(倒数第一层)，此时只要套用公式(2^left - 1) + 1 + 右子树节点个数
     *       1
     *     /   \
     *    2     3
     *   /
     *  4
     * 如果left!=right, 说明右子树是满二叉树(倒数第二层)，此时只要套用公式(2^right - 1) + 1 + 左子树节点个数
     * @param root
     * @return
     */
    // 方法语义：返回以root为根节点的二叉树的节点个数
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if (left == right) {
            return countNodes2(root.right) + (1 << left);
        } else {
            return countNodes2(root.left) + (1 << right);
        }
    }
    // 统计树的层数:因为已经声明了完全二叉树，最后一层节点居左，所以最深的左节点的深度就是树的层数
    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }
}
