package top.xiaotian.algorithms.twoPointer.slidingWindow;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 *
 * 示例：
 *
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3
 */
public class MinSubArrayLen {
    /**
     * 滑动窗口
     * 时间O(n)
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        // nums[l...r]区间是滑动窗口
        int l = 0, r = -1;// 初始状态
        int sum = 0;
        int res = Integer.MAX_VALUE;

        while (l < nums.length) {
            if (r + 1 < nums.length && sum < s) {// 扩展右边界
                r++;
                sum += nums[r];
            } else {// 左边界移动
                sum -= nums[l];
                l++;
            }

            if (sum >= s) {
                res = Math.min(res, r - l + 1);
            }
        }
        if (res == Integer.MAX_VALUE) {
            return 0;
        }
        return res;
    }
}
