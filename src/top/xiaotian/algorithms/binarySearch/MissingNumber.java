package top.xiaotian.algorithms.binarySearch;

/**
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= 数组长度 <= 10000
 */
public class MissingNumber {
  public int missingNumber(int[] nums) {
    /**
     * nums[i]=i说明它是左连续数组，nums[i]!=i说明它是右连续数组
     * 若 nums[m]=m ，则 “右子数组的首位元素” 一定在闭区间 [m + 1, j]中，因此执行 i = m + 1
     * 若 nums[m]!=m，则 “左子数组的末位元素” 一定在闭区间 [i, m - 1]中，因此执行 j = m - 1
     * 退出循环：left指向右子数组的首位，right指向左子数组的末位
     */
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == mid) {// 右子数组首位元素一定在右
        left = mid + 1;
      } else {// 左子数组末尾元素一定在左
        right = mid - 1;
      }
    }
//    return left;
    return right + 1;
  }
}
