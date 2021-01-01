package top.xiaotian.algorithms.sort.practice;

/**
 * 数组中的逆序对
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
        int[] tmp = new int[r - l + 1];
        System.arraycopy(nums, l, tmp, 0, r - l + 1);

        // 两个区间i->[l...mid]  j->[mid+1...r]
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                nums[k] = tmp[j - l];
                j++;
            } else if (j > r) {
                nums[k] = tmp[i - l];
                i++;
            } else if (tmp[i - l] <= tmp[j - l]) {
                nums[k] = tmp[i - l];
                i++;
            } else {
                // 左区间大于右区间，说明是逆序对；同时两区间是排好序的，可以将左区间剩下元素相加
                nums[k] = tmp[j - l];
                count += mid - i + 1;
                j++;
            }
        }
    }
}
