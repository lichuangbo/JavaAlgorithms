package top.xiaotian.algorithms.dp.sub_sequence;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/3/17  376
 */
public class LongestIncreaseSubsequence {
    /**
     * 动态规划
     * 时间   O(n^2)
     * 空间   O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        // dp[i]表示以nums[i]结尾，能组成的最长严格递增子序列的长度
        int[] dp = new int[len];
        // 初始化：每一个单独的元素都可以作为一个递增子序列，初始化为1
        Arrays.fill(dp, 1);

        int res = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {// 状态更新前置条件：后者比它要大，也就是形成了严格递增
                    dp[i] = Math.max(dp[i], dp[j] + 1);// 不断取 以下标j元素结尾的子序列能组成的最长递增子序列长度，比较得到最大值，+1就是当前结果
                }
            }

            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 动态规划 + 二分搜索
     * 优化点：
     *  1.每轮计算中，需要通过线性遍历 [0,k) 区间元素来得到 dp[k] 。我们考虑：是否可以通过重新设计状态定义，使整个 dp 为一个排序列表；
     *  2.这样在计算每个 dp[k] 时，就可以通过二分法遍历 [0,k) 区间元素，将此部分复杂度由 O(N) 降至 O(logN)
     * 时间   O(n*logn)
     * 空间   O(n)
     */
    public int lengthOfLIS2(int[] nums) {
        int len = nums.length;
        // 维护一个列表 tails，其中每个元素 tails[k] 的值代表 长度为 k+1 的子序列尾部元素的值
        int[] tails = new int[len];
        int res = 0;
        // 在遍历计算每个 tails[k]，不断更新长度为 [1,k] 的子序列尾部元素值，始终保持每个尾部元素值最小
        for (int i = 0; i < len; i++) {
            int l = 0, r = res;
            while (l < r) {
                int m = (l + r) / 2;
                if (tails[m] < nums[i]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            tails[l] = nums[i];
            if (res == r) {
                res++;
            }
        }
        return res;
    }
}
