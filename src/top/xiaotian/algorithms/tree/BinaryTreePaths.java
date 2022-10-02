package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 输入:
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/26 8:55
 * @Description: 描述:
 */
public class BinaryTreePaths {
  // 方法语义：返回以root为根节点的二叉树的所有路径
  // 时间复杂度O(n2), 每个节点遍历一次，同时每个路径也遍历了一次
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    // 递归终止条件：将根节点值添加到集合中
    if (root.left == null && root.right == null) {
      res.add(String.valueOf(root.val));
      return res;
    }

    // 递归遍历左子树，拿到左子树的结果集，如["2->5"]
    List<String> leftStr = binaryTreePaths(root.left);
    // 把root节点拼接进左子树的路径中
    for (String path : leftStr) {
      StringBuilder sb = new StringBuilder();
      sb.append(root.val).append("->").append(path);
      res.add(sb.toString());
    }
    // 递归遍历右子树，拿到右子树的结果集，如["3"]
    List<String> rightStr = binaryTreePaths(root.right);
    for (String path : rightStr) {
      StringBuilder sb = new StringBuilder();
      sb.append(root.val).append("->").append(path);
      res.add(sb.toString());
    }
    return res;
  }

  // 层序遍历
  public List<String> binaryTreePaths2(TreeNode root) {
    List<String> paths = new ArrayList<>();
    if (root == null) {
      return paths;
    }
    Queue<TreeNode> nodeQueue = new LinkedList<>();
    Queue<String> pathQueue = new LinkedList<>();

    nodeQueue.offer(root);
    pathQueue.offer(String.valueOf(root.val));

    while (!nodeQueue.isEmpty()) {
      TreeNode node = nodeQueue.poll();
      String path = pathQueue.poll();

      if (node.left == null && node.right == null) {
        paths.add(path);
      }
      if (node.left != null) {
        nodeQueue.offer(node.left);
        pathQueue.offer(path + "->" + node.left.val);
      }

      if (node.right != null) {
        nodeQueue.offer(node.right);
        pathQueue.offer(path + "->" + node.right.val);
      }
    }
    return paths;
  }

  // 回溯
  private List<String> res;

  public List<String> binaryTreePaths3(TreeNode root) {
    res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    help(root, new LinkedList<>());
    return res;
  }

  private void help(TreeNode root, LinkedList<String> curr) {
    curr.add(String.valueOf(root.val));
    if (root.left == null && root.right == null) {
      res.add(String.join("->", curr));
      return;
    }

    if (root.left != null) {
      help(root.left, curr);
      curr.removeLast();
    }
    if (root.right != null) {
      help(root.right, curr);
      curr.removeLast();
    }
  }
}
