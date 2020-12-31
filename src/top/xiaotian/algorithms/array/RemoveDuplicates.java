package top.xiaotian.algorithms.array;

import java.util.Arrays;

/**
 * 删除排序数组中的重复项
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class RemoveDuplicates {
    /***
     * 时间O(n2)
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    nums[j] = Integer.MIN_VALUE;
                }
            }
        }

        int m = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MIN_VALUE) {
                int tmp = nums[i];
                nums[i] = nums[m];
                nums[m] = tmp;
                m++;
            }
        }
        return m;
    }

    /***
     * 快慢指针用法！！！
     * 时间O(n)
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int k = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[k] != nums[i]){
                k++;
                nums[k] = nums[i];
            }
        }
        return k + 1;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        RemoveDuplicates test = new RemoveDuplicates();
        int res = test.removeDuplicates2(arr);
        System.out.println(res + ", " + Arrays.toString(arr));
    }
}
