package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

/**
 * 543. 二叉树的直径 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * 示例 : 给定二叉树
 *
 * 1 / \ 2   3 / \ 4   5 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class DiameterOfBinaryTree {

  private int res;

  // 错误：直径的计算不一定经过根节点，所以需要记录所有情况(不经过根)中最大值
  public int diameterOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    // root直径=左子树高度+右子树高度
    return depth(root.left) + depth(root.right);
  }
  // 树高计算
  public int depth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(depth(root.left), depth(root.right));
  }

  public int diameterOfBinaryTree2(TreeNode root) {
    dfs(root);
    return res;
  }

  private int dfs(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = dfs(root.left);
    int right = dfs(root.right);
    // 在递归所有节点时，记录最大值
    res = Math.max(left + right, res);
    return Math.max(left, right) + 1;
  }
}
