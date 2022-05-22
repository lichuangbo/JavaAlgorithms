package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.*;

/**
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，
 * 第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class LevelOrder {
  // 层序遍历+双端队列存储
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    Deque<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while(!queue.isEmpty()) {
      LinkedList<Integer> tmp = new LinkedList<>();
      int size = queue.size();
      for(int i = 0; i < size; i++) {
        TreeNode node = queue.pollFirst();
        if(res.size() % 2 == 0) {
          tmp.addLast(node.val); // 偶数层 -> 队列头部
        } else {
          tmp.addFirst(node.val); // 奇数层 -> 队列尾部
        }
        if(node.left != null) {
          queue.add(node.left);
        }
        if(node.right != null) {
          queue.add(node.right);
        }
      }
      res.add(tmp);
    }
    return res;
  }

  // 层序遍历+奇数层翻转
  public List<List<Integer>> levelOrder2(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null)   {
      return res;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> list = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode temp = queue.poll();
        list.add(temp.val);
        if (temp.left != null)
          queue.add(temp.left);
        if (temp.right != null)
          queue.add(temp.right);
      }
      if (res.size() % 2 == 1) {
        Collections.reverse(list);
      }
      res.add(list);
    }
    return res;
  }
}
