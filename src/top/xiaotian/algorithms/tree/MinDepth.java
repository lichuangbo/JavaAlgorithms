package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/25 15:55
 * @Description: 描述:
 */
public class MinDepth {
  // 方法语义：查找以root节点为根节点的二叉树的最小深度
  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int leftMinDepth = minDepth(root.left);
    int rightMinDepth = minDepth(root.right);
    /**
     *    3
     *   / \
     * nil  5
     * 根节点到叶子节点的最短路径，他必须得是一个叶子节点,所以遇到这种情况只能在右侧的树中选择最短路径
     */
    if (root.left == null) {
      return 1 + rightMinDepth;
    } else if (root.right == null) {
      return 1 + leftMinDepth;
    } else {
      return Math.min(leftMinDepth, rightMinDepth) + 1;
    }
  }

  // 层序遍历
  public int minDepth2(TreeNode root) {
    if (root == null)
      return 0;

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.addLast(root);
    int res = 0;
    while (!queue.isEmpty()) {
      res++;
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode tmpNode = queue.pollFirst();
        if (tmpNode.left == null && tmpNode.right == null) {
          return res;
        }
        if (tmpNode.left != null)
          queue.addLast(tmpNode.left);
        if (tmpNode.right != null)
          queue.addLast(tmpNode.right);
      }
    }
    return res;
  }
}
