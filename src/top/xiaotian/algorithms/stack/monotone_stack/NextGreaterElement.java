package top.xiaotian.algorithms.stack.monotone_stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 下一个更大元素问题
 *
 * @author lichuangbo
 * @date 2022/9/13
 */
public class NextGreaterElement {

  /**
   * 496. 下一个更大元素 I nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
   *
   * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
   *
   * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素
   * 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
   *
   * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
   *
   *
   *
   * 示例 1：
   *
   * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2]. 输出：[-1,3,-1] 解释：nums1 中每个值的下一个更大元素如下所述： - 4
   * ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。 - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。 -
   * 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
   */
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Deque<Integer> deque = new ArrayDeque<>();
    int len1 = nums1.length;

    // nums1数组映射，便于快速根据数组元素找到对应下标
    Map<Integer, Integer> memoMap = new HashMap<>();
    for (int i = 0; i < len1; i++) {
      memoMap.put(nums1[i], i);
    }

    int[] res = new int[len1];
    // 初始化（因为更新操作是在if条件中触发的，如果没有触发需要给到默认值）
    Arrays.fill(res, -1);
    for (int i = 0; i < nums2.length; i++) {
      // 维护一个单调栈：栈顶到栈底->元素单调递增的顺序
      while (!deque.isEmpty() && deque.peekLast() < nums2[i]) {
        // nums1是nums2的子集，说明出栈元素并不一定会在nums1中，这里需要先判断下
        if (memoMap.containsKey(deque.peekLast())) {
          // 拿到出栈元素的下标，准备记录结果
          Integer index = memoMap.get(deque.peekLast());
          // 记录结果：当前遍历的元素
          res[index] = nums2[i];
        }
        deque.pollLast();
      }
      deque.addLast(nums2[i]);
    }

    return res;
  }

  /**
   * 503. 下一个更大元素 II 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素
   * 。
   *
   * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
   *
   *
   *
   * 示例 1:
   *
   * 输入: nums = [1,2,1] 输出: [2,-1,2] 解释: 第一个 1 的下一个更大的数是 2； 数字 2 找不到下一个更大的数； 第二个 1
   * 的下一个最大的数需要循环搜索，结果也是 2。
   */
  public int[] nextGreaterElements(int[] nums) {
    int len = nums.length;
    int[] res = new int[len];
    Arrays.fill(res, -1);
    Deque<Integer> deque = new ArrayDeque<>();
    // 滚动数组，只要遍历两遍就可以和 每日温度 处理思路一致
    for (int i = 0; i < len * 2; i++) {
      while (!deque.isEmpty() && nums[deque.peekLast() % len] < nums[i % len]) {
        Integer index = deque.pollLast();
        res[index] = nums[i % len];
      }
      deque.addLast(i % len);
    }
    return res;
  }

}
