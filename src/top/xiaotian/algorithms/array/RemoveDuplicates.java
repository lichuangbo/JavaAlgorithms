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
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    // 27. 移除元素（移除所有等于val的元素，返回移除后数组个数）
    public int removeElement(int[] nums, int val) {
        int j = nums.length - 1;
        // 前半段有效，后半段无效，进行交换操作
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                swap(nums, i--, j--);
            }
        }
        return j + 1;

//        int len = nums.length;
//        for (int i = 0; i < len;) {
//            if (nums[i] == val) {
//                for (int j = i; j < nums.length - 1; j++) {
//                    nums[j] = nums[j + 1];
//                }
//                len--;
//            } else {
//                i++;
//            }
//            System.out.println(len);
//        }
//        return len;

//        int fast = 0, slow = 0;
//        while (fast < nums.length) {
//            if (nums[fast] != val) {
//                nums[slow] = nums[fast];
//                slow++;
//            }
//            fast++;
//        }
//        return slow;
    }
    void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 时间O(n2)
    public int removeDuplicates3(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1;) {
            if (nums[i] == nums[i + 1]) {
                // 遇到相同元素即开始搬移数据
                for (int j = i + 1; j < nums.length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                // 遇见相同元素，长度减一
                len--;
            } else {
                // 不相同再更新i，避免遗漏 [1, 1, 1]
                i++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        RemoveDuplicates test = new RemoveDuplicates();
        int res = test.removeDuplicates3(arr);
        System.out.println(res + ", " + Arrays.toString(arr));
    }
}
