package top.xiaotian.algorithms.twoPointer.slidingWindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 */
public class MaxSlidingWindow {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (k == 0) {
      return new int[0];
    }
    int[] res = new int[nums.length - k + 1];
    int[] tmp = new int[k];
    for (int m = 0; m < nums.length - k + 1; m++) {
      System.arraycopy(nums, m, tmp, 0, k);
      res[m] = help(tmp);
    }
    return res;
  }

  private int help(int[] nums) {
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      if (max < num) {
        max = num;
      }
    }
    return max;
  }

  public int[] maxSlidingWindow2(int[] nums, int k) {
    if (nums.length == 0 || k == 0) {
      return new int[0];
    }
    // 双端队列：维护窗口内元素的递减队列
    Deque<Integer> deque = new LinkedList<>();
    int[] res = new int[nums.length - k + 1];
    // 当i小于0时，窗口还未形成；这时候也需要记录递减队列
    for (int j = 0, i = 1 - k; j < nums.length; i++, j++) {
      // 保持 deque 递减
      while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
        deque.removeLast();
      }
      // 如果左区间移除的元素刚好是最大值，同步删除递减队列最大值
      if (i > 0 && !deque.isEmpty() && deque.peekFirst() == nums[i - 1]) {
        deque.removeFirst();
      }
      deque.addLast(nums[j]);
      // 记录窗口最大值
      if (i >= 0) {
        res[i] = deque.peekFirst();
      }
    }
    return res;
  }
}
