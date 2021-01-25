package top.xiaotian.algorithms.recursion;

import top.xiaotian.util.TreeNode;

import java.util.Stack;

/**
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 * 示例：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/25
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int res = 0;
        // 正确表达左叶子的含义！  root.left==null && root.right == null表示所有叶子节点，然后想怎么筛掉右叶子，跑偏了
        if (root.left != null && root.left.left == null && root.left.right == null) {
            res = root.left.val;
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right) + res;
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            if (tmp.left != null && tmp.left.left == null && tmp.left.right == null) {
                res += tmp.left.val;
            }
            if (tmp.left != null) {
                stack.push(tmp.left);
            }
            if (tmp.right != null) {
                stack.push(tmp.right);
            }
        }
        return res;
    }
}
