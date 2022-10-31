package top.xiaotian.algorithms.tree.binary_search_tree;

import top.xiaotian.util.TreeNode;

/**
 * 面试题 04.06. 后继者
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 *
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 *
 * 输入: root = [2,1,3], p = 1
 *
 *   2
 *  / \
 * 1   3
 *
 * 输出: 2
 * 示例 2:
 * @author lichuangbo
 * @date 2022/10/13
 */
public class InorderSuccessor {
  // 方法语义：返回以root为根节点的BST树中p的后继节点
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (root == null) {
      return null;
    }

    // 当前节点值>寻找节点值，它的后继节点只能是当前节点或者在右子树中
    if (root.val > p.val) {
      TreeNode tmpNode = inorderSuccessor(root.left, p);
      // 右子树中没有找到，那肯定是当前节点;
      return tmpNode == null ? root : tmpNode;
    }
    // <寻找节点值，后继节点只能在左子树中
    return inorderSuccessor(root.right, p);
  }
}
