package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 637. 二叉树的层平均值
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 * 解释：第 0 层的平均值为 3,第 1 层的平均值为 14.5,第 2 层的平均值为 11 。
 * 因此返回 [3, 14.5, 11] 。
 * 示例 2:
 *
 *
 *
 * 输入：root = [3,9,20,15,7]
 * 输出：[3.00000,14.50000,11.00000]
 *
 *
 * 提示：
 *
 * 树中节点数量在 [1, 104] 范围内
 * -231 <= Node.val <= 231 - 1
 */
public class AverageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> resList = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            // 避免使用Double，会存在装箱拆箱的开销
            // 每一层的和有可能溢出，直接使用double变量存储每一层的和，会避免出现这种情况，因为double表示范围更广
            double sum = 0.0;
            for (int i = 0; i < size; i++) {
                TreeNode tmpNode = deque.pollFirst();
                sum += tmpNode.val;
                if (tmpNode.left != null) {
                    deque.offerLast(tmpNode.left);
                }
                if (tmpNode.right != null) {
                    deque.offerLast(tmpNode.right);
                }
            }
            resList.add(sum / size);
        }
        return resList;
    }
}
