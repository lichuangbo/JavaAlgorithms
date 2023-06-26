package top.xiaotian.algorithms.array;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 * 剑指 Offer 66. 构建乘积数组
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 *
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 *
 *
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class ConstructArr {

    /**
     * 时间O(n)
     * 空间O(n)
     * 1 | 1  2  3  4  5  | 120
     * 1 | 1  1  3  4  5  | 60
     * 2 | 1  2  1  4  5  | 20
     * 6 | 1  2  3  1  5  | 5
     *24 | 1  2  3  4  1  | 1
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        // 求解右侧
        int[] tmp1 = new int[len];
        Arrays.fill(tmp1, 1);
        for (int i = len - 1; i > 0; i--) {
            tmp1[i - 1] = nums[i] * tmp1[i];
        }
        // 求解左侧
        int[] tmp2 = new int[len];
        Arrays.fill(tmp2, 1);
        for (int i = 0; i < len - 1; i++) {
            tmp2[i + 1] = nums[i] * tmp2[i];
        }
        // 左右两侧相乘
        for (int i = 0; i < len; i++) {
            tmp1[i] = tmp1[i] * tmp2[i];
        }
        return tmp1;
    }

    /**
     * 空间O(1)
     */
    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int left = 1;
        int right = 1;
        for (int i = 0; i < len; i++) {
            res[i] = left;
            left *= nums[i];
        }
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
