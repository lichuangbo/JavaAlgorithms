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

}
