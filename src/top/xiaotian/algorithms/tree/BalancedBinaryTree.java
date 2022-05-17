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
  // 方法语义：以root为根节点的二叉树是否为平衡二叉树
  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (Math.abs(countDepth(root.left) - countDepth(root.right)) > 1) {
      return false;
    }

    return isBalanced(root.left) && isBalanced(root.right);
  }

  // 统计树的高度
  private int countDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return Math.max(countDepth(root.left), countDepth(root.right)) + 1;
  }

  public boolean isBalanced2(TreeNode root) {
    return recur(root) != -1;
  }

  // 方法语义：先序遍历，返回以root为根节点的二叉树的高度
  private int recur(TreeNode root) {
    // 递归终止条件：
    // root节点为null，返回高度0
    // 当子树高度为-1时，代表此子树不是平衡树，因此直接返回 -1
    if (root == null) return 0;
    int left = recur(root.left);
    if (left == -1) return -1;
    int right = recur(root.right);
    if (right == -1) return -1;

    return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
  }
}
