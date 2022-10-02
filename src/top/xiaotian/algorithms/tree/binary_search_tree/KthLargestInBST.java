package top.xiaotian.algorithms.tree.binary_search_tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * 2
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 4
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 ≤ k ≤ 二叉搜索树元素个数
 */
public class KthLargestInBST {
  private List<Integer> list;
  // 中序遍历，从遍历结果中取第K大
  public int kthLargest(TreeNode root, int k) {
    list = new ArrayList<>();
    help(root);
    return list.get(list.size() - k);
  }

  private void help(TreeNode root) {
    if (root == null) {
      return;
    }

    help(root.left);
    list.add(root.val);
    help(root.right);
  }

  private int res = 0, currK = 0;
  // 以右根左的顺序遍历树，直接获取第K大
  // 扩展：以左根右顺序遍历，拿到第K小
  public int kthLargest2(TreeNode root, int k) {
    help(root, k);
    return res;
  }

  private void help(TreeNode root, int k) {
    if (root == null) {
      return;
    }

    help(root.right, k);
    if (++currK == k) {
      res = root.val;
      return;
    }
    help(root.left, k);
  }
}
