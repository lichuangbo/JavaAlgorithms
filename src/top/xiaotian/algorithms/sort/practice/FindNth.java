package top.xiaotian.algorithms.sort.practice;

import top.xiaotian.util.SwapUtil;

import java.util.Random;

/**
 * 215 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/1
 */
public class FindNth {

    private Random random;

    /**
     * 寻找nums数组中第n大的数据
     * 时间O(n)
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        random = new Random();
        return findNth(nums, 0, nums.length - 1, nums.length - k);
    }

    private int findNth(int[] nums, int l, int r, int k) {
        /**
         * [3, 2, 1, 5, 6, 4]
         * [3, 2, 1, 4, 6, 5]
         * pivot=4，p=3，及表示4是数组中从小到大排列，他排第四，也就是第三大元素
         * 如果我们要找的第k大元素，k>3,只需要继续partition右区间即可
         *                     k<3,                 左区间
         */
        int p = partition(nums, l, r);
        if (k > p) {
            return findNth(nums, p + 1, r, k);
        } else if (k < p){
            return findNth(nums, l, p - 1, k);
        } else {
            return nums[p];
        }
    }

    private int partition(int[] nums, int l, int r) {
        SwapUtil.swap(nums, l, random.nextInt(r) % (r - l + 1) + l);
        int pivot = nums[l];

        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] < pivot) {
                SwapUtil.swap(nums, i, j + 1);
                j++;
            }
        }
        SwapUtil.swap(nums, l, j);
        return j;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        int res = new FindNth().findKthLargest(nums, 2);
        System.out.println(res);
    }
}
