package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 */
public class SymmetricTree {
  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }

    return help(root.left, root.right);
  }

  // 方法语义：以p为根节点和以q为根节点的两个二叉树是否是对称的
  private boolean help(TreeNode p, TreeNode q) {
    if (p == null && q != null) {
      return false;
    } else if (p != null && q == null) {
      return false;
    } else if (p == null && q == null) {
      return true;
    }

    return help(p.left, q.right) && help(p.right, q.left) && p.val == q.val;
  }

  // BFS
  public boolean isSymmetric2(TreeNode root) {
    if (root == null) {
      return true;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root.left);
    queue.offer(root.right);
    while (!queue.isEmpty()) {
      TreeNode p = queue.poll();
      TreeNode q = queue.poll();
      if (p == null && q == null) {
        continue;
      }
      if (p == null || q == null) {
        return false;
      }
      if (p.val != q.val) {
        return false;
      }

      queue.offer(p.left);
      queue.offer(q.right);

      queue.offer(p.right);
      queue.offer(q.left);
    }
    return true;
  }
}
