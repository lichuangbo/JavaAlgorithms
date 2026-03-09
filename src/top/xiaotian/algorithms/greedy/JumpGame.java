package top.xiaotian.algorithms.greedy;

/**
 * 55. 跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 *
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 */
public class JumpGame {
    /**
     * DFS+记忆化
     */
    public boolean canJump(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return true;
        }

        // 每个位置有三种可能，0未走，1可达，2不可达
        int[] memo = new int[len];
        return dfs(nums, 0, memo);
    }

    /**
     * [2,3,1,1,4]
     * 索引0开始，可以跳1步或2步
     * 走1到3
     *  走1到1
     *      走1到1        -重叠问题
     *          走1到4√
     *  走2到1
     *      走1到1        -重叠问题
     *          走1到4√
     *  走3到4√
     * 走2到1
     *  走1到1            -重叠问题
     *      走1到4√
     */
    private boolean dfs(int[] nums, int pos, int[] memo) {
        // 当前位置已到达或超过终点
        if (pos >= nums.length - 1) {
            return true;
        }
        if (memo[pos] != 0) {
            return memo[pos] == 1;
        }

        int max = nums[pos];
        for (int i = 1; i <= max; i++) {
            int j = pos + i;
            if (j >= nums.length - 1) {
                memo[pos] = 1;
                return true;
            }
            if (dfs(nums, j, memo)) {
                memo[pos] = 1;
                return true;
            }
        }
        memo[pos] = -1;
        return false;
    }

    /**
     * 动态规划
     * 时间 O(n2)，最坏每个位置需要检查其后最多n个位置
     * 空间 O(n)，存储dp数组
     */
    public boolean canJump2(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return true;
        }

        boolean[] dp = new boolean[len];
        // 初始化，最后一个位置可达
        dp[len - 1] = true;
        for (int i = len - 2; i >= 0; i--) {
            // 从当前位置可以直接跳到或跳过终点
            if (i + nums[i] >= len - 1) {
                dp[i] = true;
                continue;
            }
            // 否则，检查所有能跳到的范围内是否可达
            for (int j = i + 1; j <= i + nums[i]; j++) {
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    /**
     * 贪心
     * 只要能到达某个位置，就能以该位置为新的起点继续向前跳。只关心最远能到哪，不关心怎么跳
     * 时间 O(n)
     * 空间 O(1)
     */
    public boolean canJump3(int[] nums) {
        int maxReach = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 当前位置已经超出了最远可达范围，说明连i都无法到达，提前结束
            if (i > maxReach) {
                return false;
            }
            // 更新最远可达位置
            maxReach = Math.max(maxReach, i + nums[i]);
            // 如果最远可达位置已覆盖终点，提前结束
            if (maxReach >= len - 1) {
                return true;
            }
        }
        return false;
    }
}
