package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树 root ，返回其最大深度。
 *
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：3
 * 示例 2：
 *
 * 输入：root = [1,null,2]
 * 输出：2
 *
 *
 * 提示：
 *
 * 树中节点的数量在 [0, 104] 区间内。
 * -100 <= Node.val <= 100
 */
public class MaxDepth {
    // 方法语义：查找以root节点为根节点的二叉树的最大深度
    public int maxDepth(TreeNode root) {
        // 递归终止条件：节点为null时深度为0
        if (root == null) {
            return 0;
        }

        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }

    // 层序遍历
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int res = 0;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            res++;
            // size必须定义变量存储，因为在层遍历中修改了deque的大小
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmpNode = deque.pollFirst();
                if (tmpNode.left != null) {
                    deque.addLast(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    deque.addLast(tmpNode.right);
                }
            }
        }
        return res;
    }
}
