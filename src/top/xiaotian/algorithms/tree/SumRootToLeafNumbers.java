package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

import java.util.*;

/**
 * 129. 求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 */
public class SumRootToLeafNumbers {
  // 基于252二叉树的所有路径题目思想，先遍历出所有的路径，然后集中求和
  public int sumNumbers(TreeNode root) {
    List<String> paths = help(root);
    int res = 0;
    for (String path : paths) {
      char[] chars = path.toCharArray();
      int k = 0;
      for (int i = chars.length - 1; i >= 0; i--) {
        res += (chars[i] - '0') * Math.pow(10, k++);
      }
    }
    return res;
  }

  private List<String> help(TreeNode root) {
    List<String> paths = new ArrayList<>();
    if (root == null) {
      return paths;
    }

    if (root.left == null && root.right == null) {
      paths.add(root.val + "");
      return paths;
    }
    List<String> leftPaths = help(root.left);
    for (String leftPath : leftPaths) {
      paths.add(root.val + leftPath);
    }
    List<String> rightPaths = help(root.right);
    for (String rightPath : rightPaths) {
      paths.add(root.val + rightPath);
    }
    return paths;
  }

  // 递归
  public int sumNumbers2(TreeNode root) {
    return dfs(root, 0);
  }

  // 方法语义：返回以root为根的二叉树的路径总和
  public int dfs(TreeNode root, int prevSum) {
    if (root == null) {
      return 0;
    }

    int sum = prevSum * 10 + root.val;
    if (root.left == null && root.right == null) {
      return sum;
    }
    return dfs(root.left, sum) + dfs(root.right, sum);
  }

  // 层序遍历
  public int sumNumbers3(TreeNode root) {
    if (root == null) {
      return 0;
    }

    Deque<TreeNode> nodeQueue = new LinkedList<>();
    Deque<Integer> sumQueue = new LinkedList<>();
    int res = 0;
    nodeQueue.addLast(root);
    sumQueue.addLast(root.val);
    while (!nodeQueue.isEmpty()) {
      TreeNode tmpNode = nodeQueue.pollLast();
      Integer tmpSum = sumQueue.pollLast();
      if (tmpNode.left == null && tmpNode.right == null) {
        res += tmpSum;
      } else {
        if (tmpNode.left != null) {
          nodeQueue.addLast(tmpNode.left);
          sumQueue.addLast(tmpSum * 10 + tmpNode.left.val);
        }
        if (tmpNode.right != null) {
          nodeQueue.addLast(tmpNode.right);
          sumQueue.addLast(tmpSum * 10 + tmpNode.right.val);
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    TreeNode treeNode = new TreeNode(new String[]{"4", "9", "0", "5", "1"});
    int i = new SumRootToLeafNumbers().sumNumbers2(treeNode);
  }
}
