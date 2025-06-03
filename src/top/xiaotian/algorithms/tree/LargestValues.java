package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 515. 在每个树行中找最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 *
 *
 * 示例1：
 *
 *
 *
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 示例2：
 *
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 *
 *
 * 提示：
 *
 * 二叉树的节点个数的范围是 [0,104]
 * -231 <= Node.val <= 231 - 1
 */
public class LargestValues {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return new ArrayList<>();

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        List<Integer> resList = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer levelMax = Integer.MIN_VALUE;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmpNode = queue.pollFirst();
                levelMax = Math.max(levelMax, tmpNode.val);
                if (tmpNode.left != null) queue.offerLast(tmpNode.left);
                if (tmpNode.right != null) queue.offerLast(tmpNode.right);
            }
            resList.add(levelMax);
        }
        return resList;
    }
}
