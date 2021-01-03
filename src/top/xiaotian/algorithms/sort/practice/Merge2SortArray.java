package top.xiaotian.algorithms.sort.practice;

import java.util.Arrays;

/**
 * 合并两个有序数组
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 示例：
 *
 * 输入：
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出：[1,2,2,3,5,6]
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/2
 */
public class Merge2SortArray {
    /**
     * 归并排序中merge()
     * 时间O(n+m+n+m+n)=O(2m+3n)
     * 空间O(m+n)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return;
        System.arraycopy(nums2, 0, nums1, m, n);
        int[] tmp = new int[m + n];
        System.arraycopy(nums1, 0, tmp, 0, m + n);

        // i维护[0...m-1]区间   j维护[m...m+n-1]区间
        int i = 0, j = m;
        for (int k = 0; k < tmp.length; k++) {
            if (i > m - 1) {
                nums1[k] = tmp[j++];
            } else if (j > m + n - 1) {
                nums1[k] = tmp[i++];
            } else if (tmp[i] < tmp[j]) {
                nums1[k] = tmp[i++];
            } else {
                nums1[k] = tmp[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 0};
        int[] nums2 = {1};
        new Merge2SortArray().merge(nums1, 1, nums2, 1);
        System.out.println(Arrays.toString(nums1));
    }
}
