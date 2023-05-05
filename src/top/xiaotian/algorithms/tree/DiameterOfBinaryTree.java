package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class DiameterOfBinaryTree {

  // 错误：直径的计算不一定经过根节点，所以需要记录所有情况(不经过根)中最大值
  public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = diameterOfBinaryTree(root.left);
    int right = diameterOfBinaryTree(root.right);
    return Math.max(left, right) + 1;
  }

  private int res;

  public int diameterOfBinaryTree2(TreeNode root) {
    dfs(root);
    return res;
  }

  // 方法语义：计算以root为根节点的二叉树，左右子树的高度最大值
  private int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = (root.left == null) ? 0 : dfs(root.left) + 1;
    int right = (root.right == null) ? 0 : dfs(root.right) + 1;
    // 在递归所有节点时，记录最大值
    res = Math.max(left + right, res);
    return Math.max(left, right);
  }
}
