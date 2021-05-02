package top.xiaotian.algorithms.sort.practice;

/**
 * 剑指 Offer 51. 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 * 示例 1:
 *
 * 输入: [7,5,6,4]        (7,5)(7,6)(7,4)(5,4)(6,4)
 * 输出: 5
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/1
 */
public class ReversePairs {

    private int count;

    public int reversePairs(int[] nums) {
        reversePairs(nums, 0, nums.length - 1);
        return count;
    }

    private void reversePairs(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }

        int mid = l + (r - l) / 2;
        reversePairs(nums, l, mid);
        reversePairs(nums, mid + 1, r);
        countPair(nums, l, mid, r);
    }

    private void countPair(int[] nums, int l, int mid, int r) {
        int i = l, j = mid + 1, k = 0;
        int[] tmp = new int[r - l + 1];
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {// 注意：这里是小于等于，因为相同元素不构成逆序对
                tmp[k++] = nums[i++];
            } else {
                // 右侧元素 比 左侧元素小，那么右侧元素肯定比左侧元素之后的都小，可以直接记为逆序对
                count += (mid - i + 1);
                tmp[k++] = nums[j++];
            }
        }
        int start = i, end = mid;
        if (j <= r) {
            start = j;
            end = r;
        }
        while (start <= end) {
            tmp[k++] = nums[start++];
        }
        System.arraycopy(tmp, 0, nums, l, r - l + 1);
    }
}
