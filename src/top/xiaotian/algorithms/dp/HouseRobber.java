package top.xiaotian.algorithms.dp;


import top.xiaotian.util.TreeNode;

import java.util.Arrays;

/**
 * 打家劫舍问题
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/2/9 10:12
 * @Description: 描述: 213 337 309
 */
public class HouseRobber {
    private int[] momo;
    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 示例 1：
     *
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        // 状态定义：考虑偷取[x...n-1]范围内的房子
        // 状态转移方程：f(0)=max(v[0]+f(2), v[1]+f(3), v[2]+f(4) ... v[n-2], v[n-1])   其中v[i]表示房子的价值，f(x)表示偷取[x...n-1]范围内的房子
        if (nums == null || nums.length == 0) {
            return 0;
        }
        momo = new int[nums.length];
        return tryRob(nums, 0);
    }
    // 递归+记忆化
    private int tryRob(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }

        if (momo[index] != 0) {
            return momo[index];
        }
        int res = Integer.MIN_VALUE;
        for (int i = index; i < nums.length; i++) {
            res = Math.max(res, nums[i] + tryRob(nums, i + 2));
        }
        momo[index] = res;
        return res;
    }
    // 动态规划1
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        // dp[i]为考虑偷取[i...len-1]区间房子的最大收益 (不一定会偷取i的)
        int[] dp = new int[len];
        // 处理最基本的情况，只偷最后一个房子
        dp[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {// 遍历，为dp[i]赋值
            for (int j = i; j < len; j++) {// [i...len-1]区间房子纳入考虑
                dp[i] = Math.max(dp[i], nums[j] + (j + 2 < len ? dp[j + 2] : 0));
            }
        }
        return dp[0];
    }
    // 动态规划2
    public int rob3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        // dp[i]为考虑偷取[0...i]区间房子的最大收益（不一定会偷取i）
        // 假设当前第i个房子， 偷i-1号房子  偷i号房子并且偷i-2号房子
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }

    // 213. 打家劫舍 II (环形房屋)
    public int robII(int[] nums) {
        // 第一个和最后一个房子只能选择偷一个
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        // copyOfRange左闭右开
        int robFirst = rob3(Arrays.copyOfRange(nums, 0, length - 1));
        int robLast = rob3(Arrays.copyOfRange(nums, 1, length));
        return Math.max(robFirst, robLast);
    }

    /**
     * 337. 打家劫舍 III (二叉树房屋)
     * 输入: [3,2,3,null,3,null,1]
     *
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     *
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        // 分类讨论的标准是：当前结点偷或者不偷
        // 由于需要后序遍历，所以先计算左右子结点，然后计算当前结点的状态值
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // dp[0]：以当前 node 为根结点的子树能够偷取的最大价值，规定 node 结点不偷
        // dp[1]：以当前 node 为根结点的子树能够偷取的最大价值，规定 node 结点偷
        int[] dp = new int[2];

        // 不偷取当前节点，左右子节点可以偷，也可以不偷   从中选取最大的
        dp[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 偷取当前节点，那就不能偷取其左右子节点（复用dp[0]语义）
        dp[1] = node.val + left[0] + right[0];
        return dp;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 1};
//        HouseRobber robber = new HouseRobber();
//        int res = robber.rob2(nums);
//        System.out.println(res);

        String[] nums = {"3", "4", "5", "1", "3", "null", "1"};
        TreeNode treeNode = new TreeNode(nums);
        HouseRobber robber = new HouseRobber();
        int res = robber.rob(treeNode);
        System.out.println(res);
    }
}
