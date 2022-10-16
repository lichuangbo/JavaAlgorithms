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
    // 先序遍历A树，因为B可能为树 A 的任意一个节点
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

    return A.val == B.val && help(A.left, B.left) && help(A.right, B.right);
  }


  /**
   * 面试题 04.10. 检查子树
   * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
   *
   * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
   *
   * 注意：此题相对书上原题略有改动。
   *
   * 示例1:
   *
   *  输入：t1 = [1, 2, 3], t2 = [2]
   *  输出：true
   */
  public boolean checkSubTree(TreeNode t1, TreeNode t2) {
    if (t2 == null) {
      return true;
    }
    if (t1 == null) {
      return false;
    }

    return help2(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
  }

  /**
   * 方法语义：判断两个二叉树是否完全相同   和{@link top.xiaotian.algorithms.tree.SameTree}是一样的了
   */
  private boolean help2(TreeNode t1, TreeNode t2) {
    /**
     * 两道题目是不一样的，区别在于这个例子，在题目1中true，在题目2中false
     * 给定的树A：
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 B：
     *
     *    4
     *   /
     *  1
     */
    if (t1 == null && t2 == null) {
      return true;
    }
    if (t1 == null || t2 == null) {
      return false;
    }

    return t1.val == t2.val && help2(t1.left, t2.left) && help2(t1.right, t2.right);
  }
}
