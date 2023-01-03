package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

/**
 * 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * 示例 3：
 * <p>
 * <p>
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -104 <= Node.val <= 104
 */
public class SameTree {
  // 方法语义：以p为根和以q为根的二叉树是否相同
  public boolean isSameTree(TreeNode p, TreeNode q) {
//    if (p == null && q != null) {
//      return false;
//    } else if (p != null && q == null) {
//      return false;
//    } else if (p == null && q == null) {
//      return true;
//    }

    // 简写方式
    if (p == null && q == null) {
      return true;
    }
    if (p == null || q == null) {
      return false;
    }

    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right) && p.val == q.val;
  }
}
