package top.xiaotian.algorithms.sort;

/**
 * 归并排序
 * 分治思想，首先递归不断地将数组从中点处分开，将长数组排序转化为短数组排序；当子数组长度为1时开始合并，将两个有序数组合并为一个有序数组
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class MergeSort {
    /***
     * 时间 O(nlogn)
     * 空间 O(nlogn)
     * 稳定排序算法
     */
    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    // 对[l...r]区间内的数组进行递归排序
    private void mergeSort(int[] arr, int l, int r) {
        // 当l>=r时，[l...r]区间最多表示一个元素，递归终止
        if (l >= r) {
            return;
        }

        int mid = l + (r - l) / 2;
        // 分治思想
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if (arr[mid] > arr[mid + 1]) {// 面对近乎有序的数据时，可以得到优化
            merge(arr, l, mid, r);
        }
    }

    // 对[l...mid]和[mid+1...r]两段区间合并成有序序列
    private void merge(int[] arr, int l, int mid, int r) {
        int[] aux = new int[r - l + 1];
//        for (int i = l; i <= r; i++) {
//            aux[i - l] = arr[i];
//        }
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
}
