package top.xiaotian.algorithms.twoPointer.fastSlowPointer;

import java.util.Arrays;

/**
 * 80. 删除有序数组中的重复项 II
 * @see RemoveDuplicatesFromSortedArray
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * <p>
 * <p>
 * 说明：
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢？
 * <p>
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3。 不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 * <p>
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前七个元素被修改为 0, 0, 1, 1, 2, 3, 3。不需要考虑数组中超出新长度后面的元素。
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class RemoveDuplicatesII {

    /**
     * 模拟删除的操作
     *
     * @param nums
     * @return
     */
    public int removeDuplicates_0(int[] nums) {
        int count = 1;
        int validLen = nums.length;
        for (int i = 0; i < validLen - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                count++;
            } else {
                count = 1;
            }

            if (count > 2) {
                // 移动数据
                for (int j = i; j < validLen - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                // 因为出现一次重复就移动一次，所以重置i下标
                i--;
                // 有效的数组元素数量-1
                validLen--;
            }
        }
        return validLen;
    }

    /***
     * 快慢指针
     * 以数组[1,1,1,2,2,3]为例，令k=slow-2 k下标代表的元素是slow之前的第一个有效数字，这个元素可以用来与 nums[fast] 进行比较，以确定 nums[fast] 是否应该被插入到当前 slow 位置
     * 1. 初始化 slow=0 fast=0，因为保留2个，所以前两个数字是肯定保留的 经过两次移动动作，nums=[1,1,1,2,2,3] slow=2 fast=2
     *                                                                               k   f
     * 2. nums[fast]（1）等于 nums[slow-2]（1）。不移动，直接将 fast 向前移动一位。nums=[1,1,1,2,2,3] slow=2 fast=3
     *                                                                            k     f
     * 3. nums[fast]（2）不等于 nums[slow-2]（1）。执行移动，并将 slow 向前移动一位。然后，将 fast 向前移动一位。nums=[1,1,2,2,2,3] slow=3,fast=4
     *                                                                                                        k     f
     * 4. nums[fast]（2）不等于 nums[slow-2]（1）。执行移动，并将 slow 向前移动一位。然后，将 fast 向前移动一位。nums=[1,1,2,2,2,3] slow=4,fast=5
     *                                                                                                          k     f
     * 5. nums[fast]（3）不等于 nums[slow-2]（1）。执行移动，并将 slow 向前移动一位。然后，将 fast 向前移动一位。nums=[1,1,2,2,3,3] slow=5,fast=6
     * 返回 slow，即新数组的长度。在这个例子中，新数组的长度为 5，新数组为 [1,1,2,2,3]。
     * 时间O(n)
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        // slow指针指向插入位置
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (slow < 2 || nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 3};
        RemoveDuplicatesII test = new RemoveDuplicatesII();
        int res = test.removeDuplicates(arr);
        System.out.println(res + ", " + Arrays.toString(arr));
    }
}
