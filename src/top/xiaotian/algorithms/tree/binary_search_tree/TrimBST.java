package top.xiaotian.algorithms.tree.binary_search_tree;

import top.xiaotian.util.TreeNode;

/**
 * 669. 修剪二叉搜索树
 *
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构
 * (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
 *
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 *
 * @author lichuangbo
 * @date 2022/9/26
 */
public class TrimBST {

  // 方法语义：修剪以root为根节点的bst，让整棵树的节点值都在[low, high]区间内，并返回修剪后的根节点
  public TreeNode trimBST(TreeNode root, int low, int high) {
    if (root == null) {
      return null;
    }

    // 当前层处理
    if (root.val < low) {// 需要修剪左区间，当前层将当前节点及左子树全部去除,将它的[修剪过的右子树]返回；如修剪0，把1和2返回
      return trimBST(root.right, low, high);
    } else if (root.val > high) {// 需要修剪右区间，同上
      return trimBST(root.left, low, high);
    } else {// root在[low,high]范围内, 当前节点保留，分别修剪其左子树与右子树
      root.left = trimBST(root.left, low, high);
      root.right = trimBST(root.right, low, high);
      return root;
    }
  }
}
