package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 513. 找树左下角的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 *
 * 假设二叉树中至少有一个节点。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: root = [2,1,3]
 * 输出: 1
 * 示例 2:
 *
 *
 *
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [1,104]
 * -231 <= Node.val <= 231 - 1
 */
public class FindBottomLeftValue {

    /**
     * 层序遍历
     * 时间   O(n) 每个节点访问一次
     * 空间   O(n) 每个元素入队一次
     */
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        int res = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = deque.pollFirst();
                if (i == 0)  {
                    res = tmp.val;
                }
                if (tmp.left != null) deque.offerLast(tmp.left);
                if (tmp.right != null) deque.offerLast(tmp.right);
            }
        }
        return res;
    }


}
