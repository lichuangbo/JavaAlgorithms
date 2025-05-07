package top.xiaotian.algorithms.twoPointer.slidingWindow;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/3
 */
public class MinSubArrayLen {

    /**
     * 暴力法 O(n2)
     * 超时
     */
    public int minSubArrayLen0(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum += nums[j];
                if (sum >= s) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 滑动窗口 写法一
     * 从空窗口状态开始扩展，扩展和收缩混合在外层循环中
     * 时间 O(n)
     */
    public int minSubArrayLen(int s, int[] nums) {
        // nums[l...r]区间是滑动窗口
        int l = 0, r = -1;// 初始状态，初始从空窗口开始
        int sum = 0;
        int res = Integer.MAX_VALUE;
        int len = nums.length;
        while (l < len) {
            if (r + 1 < len && sum < s) {// 扩展右边界
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
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 滑动窗口 写法二
     * 使用右窗口边界循环，从数组第一个元素开始，直接进入循环；外层循环专注于扩展，内层循环专注于收缩
     * 时间 O(n)
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int sum = 0;
        while (r < nums.length) {
            // 扩展右边界
            sum += nums[r];
            // 收缩左边界
            // while的必要性, sum减完最左侧窗口元素后，有可能仍然大于等于s，满足条件，必须继续移动才能找到更小的子数组（更新res）
            while (sum >= s) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
            }
            r++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
