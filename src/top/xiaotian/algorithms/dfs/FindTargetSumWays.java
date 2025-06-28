package top.xiaotian.algorithms.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 *
 * 输入：nums = [1], target = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class FindTargetSumWays {

    private int res = 0;

    /**
     * 暴力DFS：【使用全局变量维护】
     * 时间：O(2^n)，每一个元素有两个选择，+或者-
     * 空间：O(n),递归树深度不超过n
     */
    public int findTargetSumWays(int[] nums, int S) {
        help(nums, S, 0, 0);
        return res;
    }

    private void help(int[] nums, int s, int index, int curr) {
        if (index == nums.length) {
            if (curr == s) {
                res++;
            }
            return;
        }
        // 当前元素选+
        help(nums, s, index + 1, curr + nums[index]);
        // 当前元素选-
        help(nums, s, index + 1, curr - nums[index]);
    }

    /**
     * 暴力DFS：【接收返回值处理】
     */
    public int findTargetSumWays2(int[] nums, int S) {
        return dfs(nums, S, 0, 0);
    }

    // 方法语义：计算从数组的第index位置开始，当前累计和为curr的情况下，使用剩余数字能够组合成目标值target的方法数量
    private int dfs(int[] nums, int s, int index, int curr) {
        // 递归出口，索引走到最后且当前和等于target，说明得到了一个解
        if (index == nums.length) {
            return curr == s ? 1 : 0;
        }

        // 选择+
        int and = dfs(nums, s, index + 1, curr + nums[index]);
        // 选择-
        int minus = dfs(nums, s, index + 1, curr - nums[index]);
        return and + minus;
    }


    private Map<String, Integer> memo;

    /**
     * DFS:记忆化搜索
     */
    public int findTargetSumWays3(int[] nums, int S) {
        memo = new HashMap<>();
        return dfs2(nums, S, 0, 0);
    }

    private int dfs2(int[] nums, int s, int index, int curr) {
        // memo记忆化：减少重复搜索
        String key = index + "_" + curr;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (index == nums.length) {
            return curr == s ? 1 : 0;
        }
        int and = dfs2(nums, s, index + 1, curr + nums[index]);
        int minus = dfs2(nums, s, index + 1, curr - nums[index]);
        int total = and + minus;
        memo.put(key, total);
        return total;
    }

    public int findTargetSumWays4(int[] nums, int S) {
        /**
         * 加法和为a,减法和为b，那么a-b=S   同时a+b=sum，那么a=(S+sum)/2
         * 问题转化为01背包问题
         * 背包容量为a,物品在nums数组中，问有几种方法可以填满这个背包？
         * dp[i][j]表示考虑前i个元素来填满j的容量，可行的方案数
         * dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]]
         */
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // bad case: nums=[100], S=-200
        if (Math.abs(S) > sum) {
            return 0;
        }
        // 不能整除，一定分不成两队
        if ((S + sum) % 2 != 0) {
            return 0;
        }
        int len = nums.length;
        int cap = (S + sum) / 2;
        int[][] dp = new int[len + 1][cap + 1];
        // 初始化：不考虑任何元素，填满背包容量为0的方法有一种
        dp[0][0] = 1;
        // 遍历
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= cap; j++) {
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][cap];
    }

    public int findTargetSumWays5(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 边界情况检查
        if ((target + sum) % 2 != 0) return 0;
        // target绝对值大于sum，无解，不可能凑出来
        if (target > sum || target < -sum) return 0;

        int cap = (target + sum) / 2;
        if (cap < 0) return 0;

        int[] dp = new int[cap + 1];
        // 和为0的有一种方式，什么都不选；在方案数问题中，关注的是"恰好等于目标"的方案数，空集方案只对j=0有效，其他位置自然为0
        // 在标准背包问题中，关注的是"不超过容量"的最大价值，因此需要完整初始化所有容量状态
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = cap; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[cap];
    }
}
