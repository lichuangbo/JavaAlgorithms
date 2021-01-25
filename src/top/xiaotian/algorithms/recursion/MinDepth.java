package top.xiaotian.algorithms.recursion;

import top.xiaotian.util.TreeNode;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/25 15:55
 * @Description: 描述:
 */
public class MinDepth {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);
        // 题目中，根节点到叶子节点的最短路径，他必须得是一个叶子节点，根节点不算
        if (root.left == null) {
            return 1 + rightMinDepth;
        }
        if (root.right == null) {
            return 1 + leftMinDepth;
        }
        return Math.min(leftMinDepth, rightMinDepth) + 1;
    }

    public static void main(String[] args) {
//        String[] nums = {"3", "9", "20", "null", "null", "15", "7"};
        String[] nums = {"2", "null", "3", "null", "4", "null", "5", "null", "6"};
        TreeNode root = new TreeNode(nums);
        int res = new MinDepth().minDepth(root);
        System.out.println(res);
    }
}
