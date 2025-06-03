package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：root = []
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 */
public class BalancedBinaryTree {
    /**
     * 时间   O(n2) countDepth方法O(n) isBalanced方法O(n)
     * 空间   O(n) 取决于递归栈深度，最坏情况下树退化为单链表
     */
    // 方法语义：以root为根节点的二叉树是否为平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if (root == null)   return true;

        return Math.abs(countDepth(root.left) - countDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int countDepth(TreeNode root) {
        if (root == null)   return 0;

        return Math.max(countDepth(root.left), countDepth(root.right)) + 1;
    }

    /**
     * 时间   O(n) 每个节点访问一次
     * 空间   O(n)
     */
    // 剪枝，最优解
    public boolean isBalanced2(TreeNode root) {
        return help(root) != -1;
    }

    // 方法语义：求以root为根的二叉树的高度，其中如果root本身不平衡返回-1
    private int help(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = help(root.left);
        // 剪枝:左子树不平衡，直接返回-1
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = help(root.right);
        // 剪枝：右子树不平衡，直接返回-1
        if (rightDepth == -1) {
            return -1;
        }
        // 左右子树高度相差大于1，返回-1
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
