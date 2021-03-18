package top.xiaotian.algorithms.dp;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。


 示例 1：

 输入：nums = [10,9,2,5,3,7,101,18]
 输出：4
 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/3/17  376
 */
public class LIS {
    public int lengthOfLIS(int[] nums) {
        // dp[i]表示以第i个数字为结尾的最长上升子序列的长度
        // 在[0...i]的范围内，选择数字nums[i]可以获得的最长上升子序列的长度
        int[] dp = new int[nums.length];
        /**
         * 10, 9, 2, 5, 3, 7, 101, 18
         * 1   1  1  1  1  1   1   1    初始状态：不组合，仅自己
         * 1   1  1  2 => 5比10、9小,不更新，比2大更新为dp[2]+1
         */
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 1;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        LIS lis = new LIS();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int res = lis.lengthOfLIS(nums);
        System.out.println(res);
    }
}
