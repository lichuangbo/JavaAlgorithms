package top.xiaotian.algorithms.sort.practice;

import top.xiaotian.util.ListNode;

import java.util.Arrays;
import java.util.PriorityQueue;

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

    /**
     * 时间O(m+n)
     * 空间O(m+n)
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] merge2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return nums2;
        if (nums2 == null || nums2.length == 0) return nums1;
        int i = 0, j = 0, k = 0;
        int len1 = nums1.length, len2 = nums2.length;
        int[] res = new int[len1 + len2];
        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                res[k++] = nums1[i++];
            } else {
                res[k++] = nums2[j++];
            }
        }
        int st = i, en = len1;
        boolean flag = false;
        if (j < len2) {
            st = j;
            en = len2;
            flag = true;
        }
        while (st < en) {
            if (flag) {
                res[k++] = nums2[st++];
            } else {
                res[k++] = nums1[st++];
            }
        }
        return res;
    }

    /**
     * 清晰明了，时空间复杂度不变
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] merge3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return nums2;
        if (nums2 == null || nums2.length == 0) return nums1;
        int i = 0, j = 0;
        int len1 = nums1.length, len2 = nums2.length;
        int[] res = new int[len1 + len2];
        for (int k = 0; k < res.length; k++) {
            if (i > len1 - 1) {
                res[k] = nums2[j++];
            } else if (j > len2 - 1) {
                res[k] = nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                res[k] = nums1[i++];
            } else {
                res[k] = nums2[j++];
            }
        }
        return res;
    }

    /**
     * 合并k个有序数组--普通for循环
     * 时间复杂度O(k*N)
     * @param sortArrays
     * @return
     */
    public int[] merge4(int[][] sortArrays) {
        int[] res = null;
        for (int i = 0; i < sortArrays.length; i++) {
            res = merge3(res, sortArrays[i]);
        }
        return res;
    }

    /**
     * 合并k个有序数组--归并思想
     * 时间复杂度O(kn*log(k))
     * @param sortArrays
     * @return
     */
    public int[] merge5(int[][] sortArrays) {
        return mergeSort(sortArrays, 0, sortArrays.length - 1);
    }

    private int[] mergeSort(int[][] sortArrays, int l, int r) {
        if (l == r) return sortArrays[l];
        if (l > r) return null;

        int m = l + (r - l) / 2;
        int[] nums1 = mergeSort(sortArrays, l, m);
        int[] nums2 = mergeSort(sortArrays, m + 1, r);
        return merge3(nums1, nums2);
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4};
        int[] nums2 = {8,9};
        Merge2SortArray array = new Merge2SortArray();
//        array.merge(nums1, 1, nums2, 1);
//        System.out.println(Arrays.toString(nums1));

//        int[] res = array.merge2(nums1, nums2);
//        System.out.println(Arrays.toString(res));

//        int[] res = array.merge3(nums1, nums2);
//        System.out.println(Arrays.toString(res));

        int[][] sortArrays = new int[4][];
        sortArrays[0] = new int[]{1, 3, 6};
        sortArrays[1] = new int[]{2};
        sortArrays[2] = new int[]{5, 7, 9};
        sortArrays[3] = new int[]{2, 4, 8};
        int[] res = array.merge5(sortArrays);
        System.out.println(Arrays.toString(res));
    }
}
