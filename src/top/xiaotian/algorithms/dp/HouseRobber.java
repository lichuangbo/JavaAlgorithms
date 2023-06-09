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
    /**
     * 198. 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     *
     *
     * 示例 1：
     *
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2：
     *
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     */
    public int rob(int[] nums) {
        // 状态定义：考虑偷取[x...n-1]范围内的房子
        // 状态转移方程：f(0)=max(v[0]+f(2), v[1]+f(3), v[2]+f(4) ... v[n-2], v[n-1])   其中v[i]表示房子的价值，f(x)表示偷取[x...n-1]范围内的房子
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums, 0, memo);
    }

    // 递归+记忆化
    // 考虑去抢劫[index, nums.length)范围内的房子
    private int tryRob(int[] nums, int index, int[] memo) {
        if (index >= nums.length) {
            return 0;
        }

        if (memo[index] != -1) {
            return memo[index];
        }
        int res = Integer.MIN_VALUE;
        for (int i = index; i < nums.length; i++) {
            // 抢劫编号为i的这个房屋，并且考虑之后的范围的抢劫
            res = Math.max(res, nums[i] + tryRob(nums, i + 2, memo));
        }
        memo[index] = res;
        return res;
    }

    /**
     * 动态规划：自顶向下考虑
     * 时间O(n^2)
     */
    public int rob2(int[] nums) {
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

    /**
     * 动态规划：自低向上考虑
     * 时间O(n)
     */
    public int rob3(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        // dp[i]为考虑偷取[0...i]区间房子的最大收益（不一定会偷取i）
        // 考虑偷取当前的第i个房子，从方法语义来看可以从[0, i-2]拿最大收益   不考虑当前的第i个房子，拿[0. i-1]区间收益
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
        if (length == 1) {
            return nums[0];
        }
        // copyOfRange左闭右开
        int robFirst = rob3(Arrays.copyOfRange(nums, 0, length - 1));
        int robLast = rob3(Arrays.copyOfRange(nums, 1, length));
        return Math.max(robFirst, robLast);
    }

    /**
     * 337. 打家劫舍 III
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     *
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     *
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     * 示例 1:
     *      3
     *     / \
     *    2   3
     *     \   \
     *      3   1
     * 输入: root = [3,2,3,null,3,null,1]
     * 输出: 7
     * 解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
     * 示例 2:
     *
     *
     *
     * 输入: root = [3,4,5,1,3,null,1]
     * 输出: 9
     * 解释:小偷一晚能够盗取的最高金额 4 + 5 = 9
     *
     *
     * 提示：
     *
     * 树的节点数在[1, 104] 范围内
     * 0 <= Node.val <= 104
     */
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    // 方法语义：遍历以root为根的二叉树，将考虑偷取该节点和不偷取该节点的最大收益分别放置到数组0下标和1下标上
    private int[] dfs(TreeNode root) {
        // dfs出口
        if (root == null) {
            return new int[]{0, 0};
        }

        // 后序遍历：因为当前层的决定是由左右孩子决定的
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        // 当前层要做的事
        int[] dp = new int[2];
        // dp[0] 记录偷取当前节点（为了避免报警，只能选择不偷取其孩子节点的收益；同时孩子节点不相连，可以都偷取）
        dp[0] = root.val + left[1] + right[1];
        // dp[1] 记录不偷取当前节点（当前节点不偷取，那么孩子节点都可以偷取；从方法语义来看，要从左右孩子节点中选出最大的就行）
        dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return dp;
    }
}
