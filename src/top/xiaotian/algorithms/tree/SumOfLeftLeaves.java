package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
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
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 24
 * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * 示例 2:
 *
 * 输入: root = [1]
 * 输出: 0
 *
 *
 * 提示:
 *
 * 节点数在 [1, 1000] 范围内
 * -1000 <= Node.val <= 1000
 */
public class SumOfLeftLeaves {
    /**
     * 时间 O(n)
     * 空间 O(n)  递归栈深度相关， 最坏情况下树退化为链表
     */
    // 方法语义：返回以root为根节点的二叉树的所有左叶子之和
    // 左子树返回0，右子树返回15，需要加上以根3的左叶子9
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)   return 0;

        int sum = 0;
        // 正确表达左叶子的含义！  root.left==null && root.right == null表示所有叶子节点，然后想怎么筛掉右叶子，跑偏了
        // 不能站在单独的叶子节点上判断，比如9，已经没办法判断它是左叶子了（指针没有往回指），需要从上层节点出去看
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum = root.left.val;
        }
        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);
        return sum;
    }

    // 先序遍历
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

    // 层序遍历
    public int sumOfLeftLeaves3(TreeNode root) {
        if (root == null)   return 0;

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        int sum = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = deque.pollFirst();
                if (tmp.left != null && tmp.left.left == null && tmp.left.right == null) {
                    sum += tmp.left.val;
                }
                if (tmp.left != null)   deque.offerLast(tmp.left);
                if (tmp.right != null)  deque.offerLast(tmp.right);
            }
        }
        return sum;
    }
}
