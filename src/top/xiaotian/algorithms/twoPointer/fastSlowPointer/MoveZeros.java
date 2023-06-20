package top.xiaotian.algorithms.twoPointer.fastSlowPointer;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * 进阶：你能尽量减少完成的操作次数吗？
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class MoveZeros {
    /***
     * 快慢指针
     * 时间O(n)
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

    /**
     * 冒泡
     * @see top.xiaotian.algorithms.sort.BubbleSort
     * 时间O(n^2)
     */
    public void moveZeroes3(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (nums[j] == 0) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
            }
        }
    }
}
