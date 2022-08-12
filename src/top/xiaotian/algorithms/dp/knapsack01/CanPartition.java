package top.xiaotian.algorithms.dp.knapsack01;

/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/2/10 9:58
 * @Description: 描述: 322  377  474  139  494
 */
public class CanPartition {
    // memo[i][c] 表示使用索引[0...i]的这些元素，是否可以完全填充一个容量为c的背包
    // 0表示未计算， 1表示不可以填充， 2表示可以填充
    private int[][] memo;

    public boolean canPartition(int[] nums) {
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

        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int len = nums.length;
        boolean[][] dp = new boolean[len][target + 1];
        // 初始化
        for (int j = 0; j <= target; j++) {
            dp[0][j] = (j == nums[0]);
        }
        // 遍历
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[len - 1][target];
    }

    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        int len = nums.length;
        boolean[][] dp = new boolean[2][target + 1];
        // 初始化
        for (int j = 0; j <= target; j++) {
            dp[0][j] = (j == nums[0]);
        }
        // 遍历
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                int newI = (i - 1) % 2;
                boolean res;
                if (j < nums[i]) {
                    res = dp[newI][j];
                } else {
                    res = dp[newI][j] || dp[newI][j - nums[i]];
                }
                dp[i % 2][j] = res;
            }
        }
        return dp[(len - 1) % 2][target];
    }

    public boolean canPartition3(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int n = nums.length, C = sum / 2;
        boolean[] dp = new boolean[C + 1];
        for (int j = 0; j <= C; j++) {
            dp[j] = (nums[0] == j);
        }
        for (int i = 1; i < n; i++) {
            for (int j = C; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[C];
    }
}
