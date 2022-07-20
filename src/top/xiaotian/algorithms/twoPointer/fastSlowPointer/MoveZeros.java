package top.xiaotian.algorithms.twoPointer.fastSlowPointer;

import java.util.Arrays;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class MoveZeros {
    /***
     * 快慢指针
     * 时间O(n)
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }

        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /***
     * 时间O(n)
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        // 以k为界限，左边是不为0的，右边的是为0的
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[k];
                nums[k] = tmp;
                k++;
            }
        }
    }
}
