package top.xiaotian.algorithms.prefixSum;

import top.xiaotian.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 */
public class PathSumIII {

    // 方法语义：在以root为根节点的二叉树中（可以包含root，也可以不包含root），寻找和为sum的路径，返回所有的路径个数
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        // 每一个节点都可以分成三部分来看，包含root的，不包含root包含其左子节点的，不包含root包含其右子节点的
        int rootCount = help(root, targetSum);
        int leftCount = pathSum(root.left, targetSum);
        int rightCount = pathSum(root.right, targetSum);
        return rootCount + leftCount + rightCount;
    }

    // 以root为根节点的二叉树，寻找和为sum的路径（一定包含root），返回符合条件的个数
    // bad case(溢出问题): [1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000] 0
    private int help(TreeNode root, long targetSum) {
        if (root == null) {
            return 0;
        }

        int res = 0;
        if (root.val == targetSum) {
            res += 1;
        }
        res += help(root.left, targetSum - root.val);
        res += help(root.right, targetSum - root.val);
        return res;
    }

    /**
     * 原题解：https://leetcode.cn/problems/path-sum-iii/solution/dui-qian-zhui-he-jie-fa-de-yi-dian-jie-s-dey6/
     * 1. 每个节点的前缀和=从根出发到该节点间的路径和
     * 2. 两节点间的路径和=两节点的前缀和之差
     * <p>
     * 只需统计出每个节点的前缀和，并从该节点出发寻找祖先节点中满足条件的，记录结果即可
     * 注意点：只能找自己的祖先节点，这就涉及到状态的恢复
     */
    public int pathSum2(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0L, 1);

        return recursionPathSum(root, sum, prefixSumCount, 0L);
    }

    private int recursionPathSum(TreeNode root, int sum, Map<Long, Integer> prefixSumCount, Long currSum) {
        if (root == null) {
            return 0;
        }

        // 统计当前路径上的和
        currSum += root.val;

        int res = 0;
        /**
         *        10
         *       /
         *      5
         *     /
         *    3
         * 节点10的前缀和=10 节点5前缀和=15  节点3前缀和=18 sum=8
         * 假设当前遍历到节点3，currSum=18，发现currSum-sum=10在前缀和中已存在，这说明找到了一个路径
         */
        res += prefixSumCount.getOrDefault(currSum - sum, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);

        // 进入下一层
        res += recursionPathSum(root.left, sum, prefixSumCount, currSum);
        res += recursionPathSum(root.right, sum, prefixSumCount, currSum);

        // 回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
}
