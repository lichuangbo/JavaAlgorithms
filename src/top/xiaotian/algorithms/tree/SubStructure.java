package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

/**
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * <p>
 * 例如:
 * 给定的树 A:
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * 给定的树 B：
 * <p>
 * 4
 * /
 * 1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 * <p>
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 * <p>
 * 0 <= 节点个数 <= 10000
 */
public class SubStructure {
  public boolean isSubStructure(TreeNode A, TreeNode B) {
    if (A == null || B == null) {
      return false;
    }
    // 先序遍历A树，因为子结构的根节点可能为树 A 的任意一个节点
    return help(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
  }

  // 方法语义：以A为根节点的树是否包含以B为根节点的树
  private boolean help(TreeNode A, TreeNode B) {
    // 树B已经匹配完成
    if (B == null) {
      return true;
    }
    // 树A越过叶子节点，匹配失败
    if (A == null) {
      return false;
    }
    if (A.val != B.val) {
      return false;
    }

    return help(A.left, B.left) && help(A.right, B.right);
  }
}
