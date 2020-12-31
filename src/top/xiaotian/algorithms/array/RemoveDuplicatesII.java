package top.xiaotian.algorithms.array;

import java.util.Arrays;

/**
 * 删除排序数组中的重复项II
 * 给定一个增序排列数组 nums ，你需要在 原地 删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class RemoveDuplicatesII {
    /***
     * 时间O(n2)
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int count = 1, len = nums.length;
        for (int i = 1; i < len; i++) {
            // 元素相同计数+1，否则重新计数
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count > 2) {
                // 计数超过2，搬移数据
                for (int j = i; j < nums.length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                // 维护len，避免搬移后的无效数据影响
                i--;
                len--;
            }
        }

        return len;
    }

    /***
     * 快慢指针用法！！！
     * 时间O(n)
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int k = 1, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }

            // 重复元素小于2时，k和i移动速度是一样的，赋值操作相当于没起效果；当大于2时i后移，k留在了上一次处理好的有效的索引处，i不断后移count变为1后重新赋值
            if (count <= 2) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        RemoveDuplicatesII test = new RemoveDuplicatesII();
        int res = test.removeDuplicates2(arr);
        System.out.println(res + ", " + Arrays.toString(arr));
    }
}
