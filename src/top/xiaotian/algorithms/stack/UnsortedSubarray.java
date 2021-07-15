package top.xiaotian.algorithms.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * 581. 最短无序连续子数组 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,6,4,8,10,9,15]
 *              2,4,6,8,9,10,15 输出：5 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4] 输出：0
 */
public class UnsortedSubarray {

  /**
   * 排序后对比两个数组 时间：O(n+nlogn+n) 空间：O(n)
   */
  public int findUnsortedSubarray(int[] nums) {
    int length = nums.length;
    int[] sort = new int[length];
    System.arraycopy(nums, 0, sort, 0, length);
    Arrays.sort(sort);

    int i = 0;
    int minIndex = -1, maxIndex = -1;
    while (i < length) {
      if (nums[i] != sort[i]) {
        if (minIndex == -1) {
          minIndex = i;
        }
        if (i > maxIndex) {
          maxIndex = i;
        }
      }
      i++;
    }
    return minIndex == -1 ? 0 : maxIndex - minIndex + 1;
  }

  /**
   * 时间: O(n)
   * 空间：O(n)
   * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/zui-duan-wu-xu-lian-xu-zi-shu-zu-by-leetcode/
   * 【方法四】
   */
  public int findUnsortedSubarray2(int[] nums) {
    Deque<Integer> deque = new ArrayDeque<>();
    int l = nums.length, r = 0;
    // 确定无序子数组最小元素下标
    for (int i = 0; i < nums.length; i++) {
      // 后边数据 小于 前边数据，数据顺序有误，出栈并寻找原数据
      while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
        l = Math.min(l, deque.pollLast());
      }
      // 后边数据 大于 前边数据，说明数据顺序正确，入栈
      deque.addLast(i);
    }
    deque.clear();
    // 确定无序子数组最大元素下标
    for (int i = nums.length - 1; i >= 0; i--) {
      while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
        r = Math.max(r, deque.pollLast());
      }
      deque.addLast(i);
    }
    return r - l > 0 ? r - l + 1 : 0;
  }


  public static void main(String[] args) {
    int[] nums = {2,6,4,8,10,9,15};
    int unsortedSubarray = new UnsortedSubarray().findUnsortedSubarray2(nums);
    System.out.println(unsortedSubarray);
  }
}
