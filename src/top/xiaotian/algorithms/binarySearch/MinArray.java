package top.xiaotian.algorithms.binarySearch;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * <p>
 * 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
 * <p>
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numbers = [3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：numbers = [2,2,2,0,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == numbers.length
 * 1 <= n <= 5000
 * -5000 <= numbers[i] <= 5000
 * numbers 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class MinArray {
  public int minArray(int[] numbers) {
    int l = 0, r = numbers.length - 1;
    while (l < r) {
      int m = (l + r) / 2;
      if (numbers[m] > numbers[r]) {// m位于左递增序列中
        l = m + 1;
      } else if (numbers[m] < numbers[r]) {// m位于右递增序列中
        r = m;
      } else {// 无法判断 m 在哪个排序数组中
        r--;
      }
    }
    return numbers[l];
  }

  public int minArray2(int[] numbers) {
    int i = 0, j = numbers.length - 1;
    while (i < j) {
      int m = (i + j) / 2;
      if (numbers[m] > numbers[j]) {
        i = m + 1;
      } else if (numbers[m] < numbers[j]) {
        j = m;
      } else {
        // 当出现 nums[m] = nums[j] 时，一定有区间 [i, m]内所有元素相等 或 区间 [m, j] 内所有元素相等（或两者皆满足）,转为线性查找
        int x = i;
        for (int k = i + 1; k < j; k++) {
          if (numbers[k] < numbers[x]) {
            x = k;
          }
        }
        return numbers[x];
      }
    }
    return numbers[i];
  }
}
