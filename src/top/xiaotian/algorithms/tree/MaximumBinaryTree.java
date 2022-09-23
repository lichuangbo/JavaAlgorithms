package top.xiaotian.algorithms.tree;

import top.xiaotian.util.TreeNode;

/**
 * 654. 最大二叉树
 *
 * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 *
 * 创建一个根节点，其值为 nums 中的最大值。 递归地在最大值 左边 的 子数组前缀上 构建左子树。 递归地在最大值 右边 的 子数组后缀上 构建右子树。
 *
 * 返回 nums 构建的 最大二叉树 。
 *
 * @author lichuangbo
 * @date 2022/9/23
 */
public class MaximumBinaryTree {

  public TreeNode constructMaximumBinaryTree(int[] nums) {
    return help(nums, 0, nums.length - 1);
  }

  private TreeNode help(int[] nums, int st, int en) {
    if (st > en) {
      return null;
    }

    int max = Integer.MIN_VALUE;
    int maxIndex = -1;
    for (int i = st; i <= en; i++) {
      if (nums[i] > max) {
        max = nums[i];
        maxIndex = i;
      }
    }
    TreeNode root = new TreeNode(max);
    root.left = help(nums, st, maxIndex - 1);
    root.right = help(nums, maxIndex + 1, en);
    return root;
  }
}
