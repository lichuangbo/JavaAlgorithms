package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 55 - I. 二叉树的深度
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 * <p>
 * 例如：
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
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
