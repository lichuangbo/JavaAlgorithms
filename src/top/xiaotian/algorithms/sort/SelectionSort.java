package top.xiaotian.algorithms.sort;

/**
 * 选择排序
 * 每轮从未排序区间选择最小的元素，将它放到已排序区间的末尾
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class SelectionSort {
    /***
     * 时间 O(n2)
     * 空间 O(1)
     * 不稳定排序算法：每次在无序集中查找最小值的做法破坏了稳定性
     */
    public void selectionSort(int[] arr) {
        int n = arr.length;
        // 未排序区间为[i, n-1]（前n-1个元素都已排序，那么剩下的那个元素必定是最大值，无序处理）
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;// 记录数组中最小值对应的下标
            // i索引前边都是有序的，从i索引以后结果集找出最小值
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 每一趟都能找到一个最小值，找到就交换位置
            int tmp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = tmp;
        }
    }
}
