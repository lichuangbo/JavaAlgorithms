package top.xiaotian.algorithms.tree.binary_search_tree;

import top.xiaotian.util.TreeNode;

/**
 * 530. 二叉搜索树的最小绝对差
 * 783. 二叉搜索树节点最小距离
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [4,2,6,1,3] 输出：1
 *
 * @author lichuangbo
 * @date 2022/9/23
 */
public class MinimumDifference {

  private TreeNode prev;
  private Integer res;
  public int getMinimumDifference(TreeNode root) {
    res = Integer.MAX_VALUE;
    help(root);
    return res;
  }

  private void help(TreeNode root) {
    if (root == null) {
      return;
    }

    help(root.left);
    if (prev != null) {
      res = Math.min(res, root.val - prev.val);
    }
    prev = root;
    help(root.right);
  }
}
