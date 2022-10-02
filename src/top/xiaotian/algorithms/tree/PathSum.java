package top.xiaotian.algorithms.tree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import top.xiaotian.util.TreeNode;

public class PathSum {

    /**
     * 112. 路径总和
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，
     * 这条路径上所有节点值相加等于目标和 targetSum 。
     * 叶子节点 是指没有子节点的节点。
     * 示例 1：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
     * 输出：true
     *
     * 时间：O(n),n为节点个数
     * 空间：O(logn),递归树的高度
     */
    // 方法语义：判断以root为根节点的二叉树，是否存在一条（从根到叶子的）路径是等于targetSum的
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
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
     * 示例 1：
     * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
     * 输出：[[5,4,11,2],[5,8,4,5]]
     *
     * 回溯
     * 时间：O(n2),路径n，每一个节点还要添加进结果n
     * 空间：O(n)
     */
    private List<List<Integer>> res;
    public List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        help(root, targetSum, new LinkedList<>());
        return res;
    }

    private void help(TreeNode root, int targetSum, LinkedList<Integer> currList) {
        if (root == null) {
            return;
        }

        // 记录当前操作路径
        currList.add(root.val);
        // 将找到的路径加入结果集
        if (targetSum == root.val && root.left == null && root.right == null) {
            res.add(new ArrayList<>(currList));
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
        }

        // 递归遍历左右子树中符合的路径
        help(root.left, targetSum - root.val, currList);
        help(root.right, targetSum - root.val, currList);
        // 当以加入该节点为前提递归寻找其左右子树后，删除该节点
        currList.removeLast();
    }

    private void help2(TreeNode root, int targetSum, LinkedList<Integer> currList) {
        currList.add(root.val);
        if (root.left == null && root.right == null && targetSum == root.val) {
            res.add(new ArrayList<>(currList));
            return;
        }

        if (root.left != null) {
            help2(root.left, targetSum - root.val, currList);
            currList.removeLast();
        }
        if (root.right != null) {
            help2(root.right, targetSum - root.val, currList);
            currList.removeLast();
        }
    }

    /**
     * 437. 路径总和 III
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     * 找出路径和等于给定数值的路径总数。
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     * 示例：
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     * 返回 3。和等于 8 的路径有:
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     */
    // 方法语义：在以root为根节点的二叉树中，寻找和为sum的路径，返回所有的路径个数
    public int pathSumIII(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        int res = 0;
        res += findPath(root, sum);// 记录路径包含root的
        res += pathSumIII(root.left, sum);// 记录路径不包含root的，包含左子树的
        res += pathSumIII(root.right, sum);// 记录路径不包含root的，包含右子树的
        return res;
    }

    // 以root为根节点的二叉树，寻找和为sum的路径（其中路径一定会包含root），返回符合条件的个数
    private int findPath(TreeNode root, int sum) {
        // 没有要求必须是到达叶子结点，可以一直搜索到空
        if (root == null) {
            return 0;
        }

        int res = 0;
        if (root.val == sum) {
            // 不能直接return，因为可能存在另外的路径也满足条件，所以需要继续计算
            res += 1;
        }
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }

    // 前缀和  https://leetcode.cn/problems/path-sum-iii/solution/dui-qian-zhui-he-jie-fa-de-yi-dian-jie-s-dey6/
    /**
     * 优化切入点为「路径只能往下」，因此如果我们转换一下，统计以每个节点为「路径结尾」的合法数量的话，配合原本就是「从上往下」进行的数的遍历
     * （最完整的路径必然是从原始根节点到当前节点的唯一路径），相当于只需要在完整路径中找到有多少个节点到当前节点的路径总和为 targetSum
     * 转化为一维前缀和问题：
     * 求解从原始起点（根节点）到当前节点 b 的路径中，有多少节点 a 满足 sum[a...b] = targetSum
     */
    public int pathSumIII2(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);

        return recursionPathSum(root, sum, prefixSumCount, 0);
    }

    private int recursionPathSum(TreeNode root, int sum, Map<Integer, Integer> prefixSumCount,  int currSum) {
        if (root == null) {
            return 0;
        }

        // 当前路径上的和
        currSum += root.val;

        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-sum,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+sum=currSum，当前点到起点的距离就是target
        int res = prefixSumCount.getOrDefault(currSum - sum, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);

        // 3.进入下一层
        res += recursionPathSum(root.left, sum, prefixSumCount, currSum);
        res += recursionPathSum(root.right, sum, prefixSumCount, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
}
