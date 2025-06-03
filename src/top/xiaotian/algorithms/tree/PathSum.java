package top.xiaotian.algorithms.tree;


import top.xiaotian.util.TreeNode;

import java.util.*;

public class PathSum {

    /**
     * 112. 路径总和
     * 已解答
     * 简单
     * 相关标签
     * premium lock icon
     * 相关企业
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
     *
     * 叶子节点 是指没有子节点的节点。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * 输出：true
     * 解释：等于目标和的根节点到叶节点路径如上图所示。
     * 示例 2：
     *
     *
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：false
     * 解释：树中存在两条根节点到叶子节点的路径：
     * (1 --> 2): 和为 3
     * (1 --> 3): 和为 4
     * 不存在 sum = 5 的根节点到叶子节点的路径。
     * 示例 3：
     *
     * 输入：root = [], targetSum = 0
     * 输出：false
     * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
     *
     *
     * 提示：
     *
     * 树中节点的数目在范围 [0, 5000] 内
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     */
    // 方法语义：判断以root为根节点的二叉树，是否存在一条（从根到叶子的）路径是等于targetSum的
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }

        // 递归的从左右子树开始寻找剩余值
        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 113. 路径总和 II
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * 叶子节点 是指没有子节点的节点。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     * 示例 2：
     *
     *
     * 输入：root = [1,2,3], targetSum = 5
     * 输出：[]
     * 示例 3：
     *
     * 输入：root = [1,2], targetSum = 0
     * 输出：[]
     *
     *
     * 提示：
     *
     * 树中节点总数在范围 [0, 5000] 内
     * -1000 <= Node.val <= 1000
     * -1000 <= targetSum <= 1000
     *
     * 回溯
     * 时间：O(n2),路径n，每一个节点还要添加进结果n
     * 空间：O(n)
     */
    public List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        help(root, targetSum, new LinkedList<>(), res);
        return res;
    }

    private void help(TreeNode root, int targetSum, LinkedList<Integer> currList, List<List<Integer>> resList) {
        if (root == null) {
            return;
        }

        // 记录当前操作路径
        currList.add(root.val);
        // 将找到的路径加入结果集
        if (targetSum == root.val && root.left == null && root.right == null) {
            resList.add(new ArrayList<>(currList));
            /**
             *        5
             *      /   \
             *     4     8
             *    /
             *   11
             *  /  \
             * 7    2  
             * 不能return的原因：
             * 当拿到第一个结果集[5,4,11,2]时，直接return会导致回退路径和目标节点不匹配
             * return后，root回退到11节点，此时currList[5,4,11]
             * root继续回退4节点，4节点的右子树为空，再回退，此时currList[5,4]
             * 此时root节点位于5，添加8节点，此时currList[5,4,8]，,路径还没有回退完但是已经在处理右侧节点了
             */
//            return;
        } else {
            // 递归遍历左右子树中符合的路径
            help(root.left, targetSum - root.val, currList, resList);
            help(root.right, targetSum - root.val, currList, resList);
        }

        // 当以加入该节点为前提递归寻找其左右子树后，删除该节点
        currList.removeLast();
    }
}
