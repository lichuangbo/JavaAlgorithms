package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 226. 翻转二叉树
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 * 示例 3：
 * <p>
 * 输入：root = []
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目范围在 [0, 100] 内
 * -100 <= Node.val <= 100
 */
public class InvertTree {
  // 方法语义：翻转以root为根的二叉树，并返回翻转后的根节点
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }

    // 中序遍历，分别翻转以root.left和root.right为根的二叉树
    TreeNode left = invertTree(root.left);
    TreeNode right = invertTree(root.right);
    // 当前层要做的是：交换其左右子节点
    root.left = right;
    root.right = left;
    return root;
  }

  // 层次遍历--直接左右交换
  public TreeNode invertTree2(TreeNode root) {
    if (root == null) {
      return null;
    }

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.addLast(root);
    while (!queue.isEmpty()){
      TreeNode tmpNode = queue.pollFirst();

      TreeNode rightTree = tmpNode.right;
      tmpNode.right = tmpNode.left;
      tmpNode.left = rightTree;
      if (tmpNode.left != null){
        queue.addLast(tmpNode.left);
      }
      if (tmpNode.right != null){
        queue.addLast(tmpNode.right);
      }
    }
    return root;
  }
}
