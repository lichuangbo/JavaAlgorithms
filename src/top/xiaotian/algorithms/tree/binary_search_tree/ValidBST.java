package top.xiaotian.algorithms.tree.binary_search_tree;

import top.xiaotian.util.TreeNode;

/**
 * 98. 验证二叉搜索树
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 小于 当前节点的数。 节点的右子树只包含 大于 当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树。 提示：
 *
 * 树中节点数目范围在[1, 104] 内 -231 <= Node.val <= 231 - 1
 *
 * @author lichuangbo
 * @date 2022/9/23
 */
public class ValidBST {

  TreeNode prev = null;

  // 避免出现val=long.max_value
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }

    // 中序遍历，看遍历过程中是否存在不符合整体升序的节点
    if (!isValidBST(root.left)) {
      return false;
    }
    if (prev != null && prev.val >= root.val) {
      return false;
    }
    prev = root;
    if (!isValidBST(root.right)) {
      return false;
    }
    return true;
  }

  public boolean isValidBST2(TreeNode root) {
    return help(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  // 递归语义：自顶向下看，以root为根的二叉搜索树中，判断树中左子节点值和右子节点值是否同时符合二叉搜索树特性
  private boolean help(TreeNode root, long min, long max) {
    if (root == null) {
      return true;
    }

    // root节点不在区间内，直接返回false
    if (root.val <= min || root.val >= max) {
      return false;
    }
    // 左子树的上界是根节点值(小于根节点)， 右子树的下界是根节点值(大于根节点)
    return help(root.left, min, root.val) && help(root.right, root.val, max);
  }


}
