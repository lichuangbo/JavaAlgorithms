package top.xiaotian.algorithms.sort.practice;

import top.xiaotian.util.SwapUtil;

import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/1
 */
public class FindNth {

  private Random random;

  /**
   * 寻找nums数组中第n大的数据
   * 时间O(n)
   */
  public int findKthLargest(int[] nums, int k) {
    random = new Random();
    int len = nums.length;
    return findNth(nums, 0, len - 1, len - k);
  }

  private int findNth(int[] nums, int l, int r, int k) {
    /**
     * [3, 2, 1, 5, 6, 4]
     * [3, 2, 1, 4, 6, 5]
     * pivot=4，p=3，即表示4是数组中从小到大排列，他排第四，是第四小元素，是第三大元素
     * 如果我们要找的第k大元素，k>p,只需要继续partition右区间即可
     *                     k<p,                 左区间
     */
    int p = partition(nums, l, r);
    if (k > p) {
      return findNth(nums, p + 1, r, k);
    } else if (k < p) {
      return findNth(nums, l, p - 1, k);
    } else {
      return nums[p];
    }
  }

  // 一次partition方法调用，会把数组中的一个元素放置到有序排列中它该放的位置上，并返回它有序排列中的索引下标
  // 这个索引下标可以理解为第p+1小的元素下标，第nums.length-p大的元素下标
  private int partition(int[] nums, int l, int r) {
    swap(nums, l, random.nextInt(r - l + 1) + l);
    int pivot = nums[l];

    int j = l;
    for (int i = l + 1; i <= r; i++) {
      if (nums[i] < pivot) {
        SwapUtil.swap(nums, i, j + 1);
        j++;
      }
    }
    swap(nums, l, j);
    return j;
  }

  private void swap(int[] nums, int l, int r) {
    int tmp = nums[l];
    nums[l] = nums[r];
    nums[r] = tmp;
  }

}
