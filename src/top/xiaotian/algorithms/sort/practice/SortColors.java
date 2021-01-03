package top.xiaotian.algorithms.sort.practice;

import top.xiaotian.util.SwapUtil;

/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/2    88 215
 */
public class SortColors {
    /**
     * 计数排序思想
     * 时间O(n)
     * 空间O(k)
     * @param nums
     */
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }

        int k = 0;
        for (int i = 0; i < count[0]; i++) {
            nums[k++] = 0;
        }
        for (int i = 0; i < count[1]; i++) {
            nums[k++] = 1;
        }
        for (int i = 0; i < count[2]; i++) {
            nums[k++] = 2;
        }
    }

    /**
     * 三路快排思想
     * 时间O(n)
     * 空间O(1)
     * @param nums
     */
    public void sortColors2(int[] nums) {
        int zero = -1;// 使nums[0...zero]区间==0
        int two = nums.length;// 使nums[two...n-1]区间==2
        for (int i = 0; i < nums.length; ) {// 活动区间使[zero+1...i-1]==1
            if (nums[i] == 1) {// i后移，纳入1区间
                i++;
            } else if (nums[i] == 2) {// 元素理应属于2区间(和2区间的前一个元素交换)
                two--;
                SwapUtil.swap(nums, i, two);
            } else {// 元素理应属于0区间(和1区间的第一个元素交换)
                zero++;
                SwapUtil.swap(nums, zero, i);
                i++;
            }
        }
    }
}
