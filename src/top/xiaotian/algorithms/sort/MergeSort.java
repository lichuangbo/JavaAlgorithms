package top.xiaotian.algorithms.sort;

import top.xiaotian.util.RandomUtil;

import java.util.Arrays;

/**
 * 归并排序
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class MergeSort {
    /***
     * 时间O(nlogn)
     * 空间O(nlogn)
     * @param arr
     */
    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    // 对[l...r]区间内的数组进行递归排序
    private void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int mid = l + (r - l) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if (arr[mid] > arr[mid + 1]) {// 面对近乎有效的数据时，可以得到优化
            merge2(arr, l, mid, r);
        }
    }

    // 对[l...mid]和[mid+1...r]两段区间合并成有序序列
    private void merge(int[] arr, int l, int mid, int r) {
        int i = l, j = mid + 1, k = 0;
        int[] aux = new int[r - l + 1];
        while (i <= mid && j <= r) {
            if (arr[i] < arr[j]) {
                aux[k++] = arr[i++];
            } else {
                aux[k++] = arr[j++];
            }
        }

        // 处理剩余区间的有序数组元素
        int start = i, end = mid;
        if (j <= r) {
            start = j;
            end = r;
        }
        while (start <= end) {
            aux[k++] = arr[start++];
        }
        // 拷贝回原数组
        System.arraycopy(aux, 0, arr, l, r - l + 1);
    }

    // 对[l...mid]和[mid+1...r]两段区间合并成有序序列
    private void merge2(int[] arr, int l, int mid, int r) {
        int[] aux = new int[r - l + 1];
        System.arraycopy(arr, l, aux, 0, r - l + 1);

        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {// 区间1处理完，此时处理区间2
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {// 区间2处理完，此时处理区间1
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {// 注意偏移值
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    // 自底向上完成归并排序  由于没有使用数组的随机访问特性，可以应用到链表归并排序中
    public void mergeSort2(int[] arr) {
        int len = arr.length;
        for (int sz = 1; sz <= len; sz += sz) {// 第一轮看一个元素，第二轮看两个元素...
            for (int i = 0; i + sz < len; i += sz + sz) {// 具体每一轮元素的起始位置
                // 第一轮[0...size-1],第二轮[size...2*size-1]
                // 对arr[i...i+size-1]和arr[i+size...i+2*size-1]两个区间进行归并
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, len - 1));
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = RandomUtil.randomInt(10000, 1, 10000);

        new MergeSort().mergeSort2(arr);
        System.out.println(Arrays.toString(arr));
    }
}
