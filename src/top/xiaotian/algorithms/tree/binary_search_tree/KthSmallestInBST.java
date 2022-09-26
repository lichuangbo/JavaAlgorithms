package top.xiaotian.algorithms.tree.binary_search_tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import top.xiaotian.util.TreeNode;

/**
 * 230. 二叉搜索树中第K小的元素
 *
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [3,1,4,null,2], k = 1 输出：1
 *
 * @author lichuangbo
 * @date 2022/9/26
 */
public class KthSmallestInBST {

  public int kthSmallest(TreeNode root, int k) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;
    while (!stack.isEmpty() || curr != null) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
      TreeNode tmpNode = stack.pop();
      k--;
      if (k == 0) {
        return tmpNode.val;
      }
      curr = tmpNode.right;
    }
    return -1;
  }

  public int kthSmallest2(TreeNode root, int k) {
    Deque<TreeNode> deque = new ArrayDeque<>();
    while (true) {
      while (root != null) {
        deque.addLast(root);
        root = root.left;
      }

      TreeNode tmp = deque.removeLast();
      if (--k == 0) {
        return tmp.val;
      }
      root = tmp.right;
    }
  }
}
