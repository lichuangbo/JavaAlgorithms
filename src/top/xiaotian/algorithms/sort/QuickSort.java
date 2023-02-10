package top.xiaotian.algorithms.sort;

import java.util.Random;

/**
 * 快速排序
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class QuickSort {

  private Random random;

  /***
   * 时间O(nlogn)
   * 空间0(nlogn)
   * 不稳定排序算法：定位pivot应该放哪时存在交换，导致不稳定
   * @param arr
   */
  public void quickSort(int[] arr) {
    random = new Random();
    quickSort(arr, 0, arr.length - 1);
  }

  // 对数组[l...r]区间进行快速排序
  private void quickSort(int[] arr, int l, int r) {
    // 当l>=r时，[l...r]区间最多表示一个元素，递归终止
    if (l >= r) {
      return;
    }

    int p = partition(arr, l, r);
    // 分治思想
    quickSort(arr, l, p - 1);
    quickSort(arr, p + 1, r);
  }

  // 对数组[l...r]区间进行partition操作：使得arr[l...p-1] < arr[p] && arr[p+1...r] > arr[p]
  private int partition(int[] arr, int l, int r) {
    // 避免分区始终一大一小不平均的情况（在近乎有序的数组中退化成O(n2)）
    // 随机选择标定点
    swap(arr, l, random.nextInt(r) % (r - l + 1) + l);
    int pivot = arr[l];

    // 使arr[l+1...j] < pivot  arr[j+1...i-1] > pivot
    int j = l;// j记录两分区的分界点下标(初始状态，[l+1...l]为空区间，[j+1...j]也为空区间)
    for (int i = l + 1; i <= r; i++) {// i是当前遍历的元素下标
      // arr[i] > pivot不操作，属于区间2；< pivot，属于区间1，要进行交换操作（和区间2的第一个元素交换，而后j后移，保证j仍位于分区分界点上）
      if (arr[i] < pivot) {
        swap(arr, i, j + 1);
        j++;
      }
    }
    // 将pivot放置到中间位置，交换
    swap(arr, l, j);
    return j;
  }

  private void swap(int[] nums, int a, int b) {
    int tmp = nums[a];
    nums[a] = nums[b];
    nums[b] = tmp;
  }
}
