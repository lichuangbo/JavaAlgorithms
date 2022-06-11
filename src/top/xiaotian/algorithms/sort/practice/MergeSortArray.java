package top.xiaotian.algorithms.sort.practice;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 合并有序数组
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/2
 */
public class MergeSortArray {
    /**
     * 88. 合并两个有序数组
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
     * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n
     * 示例 1：
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
     * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
     *
     * 时间O(n+m+n+m+n)=O(2m+3n)
     * 空间O(m+n)
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return;
        System.arraycopy(nums2, 0, nums1, m, n);
        int[] aux = new int[m + n];
        System.arraycopy(nums1, 0, aux, 0, m + n);

        // i维护[0...m-1]区间   j维护[m...m+n-1]区间
        int i = 0, j = m;
        for (int k = 0; k < aux.length; k++) {
            if (i > m - 1) {
                nums1[k] = aux[j++];
            } else if (j > m + n - 1) {
                nums1[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                nums1[k] = aux[i++];
            } else {
                nums1[k] = aux[j++];
            }
        }
    }

    /**
     * 方法返回合并后的有序数组
     */
    public int[] merge2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return nums2;
        if (nums2 == null || nums2.length == 0) return nums1;
        // i维护[0...len1-1]区间  j维护[0...len2-1]区间
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
     */
    public int[] merge3(int[][] sortArrays) {
        int[] res = null;
        for (int i = 0; i < sortArrays.length; i++) {
            res = merge2(res, sortArrays[i]);
        }
        return res;
    }

    /**
     * 合并k个有序数组--归并思想
     * 时间复杂度O(n*log(k))
     */
    public int[] merge4(int[][] sortArrays) {
        return mergeSort(sortArrays, 0, sortArrays.length - 1);
    }

    // 对[l...r]区间内的数组进行递归合并
    private int[] mergeSort(int[][] sortArrays, int l, int r) {
        if (l == r) {
            return sortArrays[l];
        }

        int m = l + (r - l) / 2;
        int[] nums1 = mergeSort(sortArrays, l, m);
        int[] nums2 = mergeSort(sortArrays, m + 1, r);
        return merge2(nums1, nums2);
    }

    class Element implements Comparable<Element>{
        int i, j, val;
        Element(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }

        @Override
        public int compareTo(Element o) {
            return this.val - o.val;
        }
    }

    /**
     * 合并k个有序数组--最小堆
     * nlog(k)
     */
    private int[] merge5(int[][] arrays) {
        int arrLen = 0;
        PriorityQueue<Element> minHeap = new PriorityQueue<>();
        for (int i = 0; i < arrays.length; i++) {
            arrLen += arrays[i].length;
            minHeap.offer(new Element(i, 0, arrays[i][0]));
        }

        int[] res = new int[arrLen];
        int idx = 0;
        while (!minHeap.isEmpty()) {
            Element top = minHeap.poll();
            res[idx++] = top.val;
            if (top.j + 1 < arrays[top.i].length) {
                top.j++;
                top.val = arrays[top.i][top.j];
                minHeap.offer(top);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        MergeSortArray array = new MergeSortArray();

        int[][] sortArrays = new int[4][];
        sortArrays[0] = new int[]{1, 3, 6};
        sortArrays[1] = new int[]{2};
        sortArrays[2] = new int[]{5, 7, 9};
        sortArrays[3] = new int[]{2, 4, 8};
        int[] res3 = array.merge3(sortArrays);
        int[] res4 = array.merge4(sortArrays);
        int[] res5 = array.merge5(sortArrays);
        System.out.println(Arrays.toString(res3));
        System.out.println(Arrays.toString(res4));
        System.out.println(Arrays.toString(res5));
    }
}
