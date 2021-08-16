package top.xiaotian.algorithms.recursion;


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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        // 叶子节点！！
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
     * 时间：O(n2),路径n，每一个节点还要添加进结果n
     * 空间：O(n)
     */
    public List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        help(root, targetSum, res, new LinkedList<>());
        return res;
    }

    private void help(TreeNode root, int targetSum, List<List<Integer>> resList, LinkedList<Integer> currList) {
        if (root == null) {
            return;
        }

        // 记录当前操作路径
        currList.add(root.val);
        // 将找到的路径加入结果集
        if (targetSum == root.val && root.left == null && root.right == null) {
            resList.add(new ArrayList<>(currList));
        }

        // 递归遍历左右子树中符合的路径
        help(root.left, targetSum - root.val, resList, currList);
        help(root.right, targetSum - root.val, resList, currList);
        // 当以加入该节点为前提递归寻找其左右子树后，删除该节点
        currList.removeLast();
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
        if (root == null) {
            return 0;
        }

        int res = 0;
        if (root.val == sum) {
            res += 1;
        }
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }

    public int pathSumIII2(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
}
