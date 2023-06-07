package top.xiaotian.algorithms.dp.knapsack01;

/**
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/2/10 9:58
 * @Description: 描述: 322  377  474  139  494
 */
public class CanPartition {
    // memo[i][c] 表示使用索引[0...i]的这些元素，是否可以完全填充一个容量为c的背包
    // 0表示未计算， 1表示不可以填充， 2表示可以填充
    private int[][] memo;

    public boolean canPartition0(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        memo = new int[nums.length][sum / 2 + 1];
        return tryPartition(nums, nums.length - 1, sum / 2);
    }

    // 使用nums[0...index]，是否可以完全填充一个容量为sum的背包
    // f(n, c)考虑将n个物品填满容量为c的背包   f(n, c) = f(i-1, c) || f(i-1, c-w[i])   不考虑第i个物品，前i-1个物品就能填满   考虑第i个物品，就能填满
    private boolean tryPartition(int[] nums, int index, int sum) {
        if (sum == 0) {
            return true;
        }
        // 背包放不下物品 或者 没有物品可选
        if (sum < 0 || index < 0) {
            return false;
        }

        if (memo[index][sum] != 0) {
            return memo[index][sum] == 2;
        }
        memo[index][sum] = (tryPartition(nums, index - 1, sum) ||
                tryPartition(nums, index - 1, sum - nums[index])) ? 2 : 1;
        return memo[index][sum] == 1;
    }

    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 不能整除的话，肯定分割不了的
        if (sum % 2 != 0) {
            return false;
        }
        int capacity = sum / 2;
        int len = nums.length;
        // 从nums数组中选取物品，来尝试填容量为capacity的背包，问能不能刚好填满？
        // capacity列要多声明一处，因为容量[0,capacity]用到了最后一个
        boolean[][] dp = new boolean[len][capacity + 1];
        // 初始化dp[0][j] 因为递推公式用到了[i-1]
        for (int j = 0; j <= capacity; j++) {
            dp[0][j] = (j != nums[0]) ? false : true;
        }
        // 遍历
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][capacity];
    }

    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int len = nums.length;
        int capacity = sum / 2;
        // 滚动数组，dp[j]表示填满容量为j的背包，问能不能刚好填满
        boolean[] dp = new boolean[capacity + 1];
        for (int j = 0; j <= capacity; j++) {
            dp[j] = (j == nums[0]);
        }
        for (int i = 1; i < len; i++) {
            for (int j = capacity; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[capacity];
    }
}
