package top.xiaotian.algorithms.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 *
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 *
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2] 输出: 2
 *
 *
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 50000
 */
public class MajorityElement {

  public int majorityElement(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.compute(num, (k, v) -> v == null ? 1 : v + 1);
    }
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > nums.length / 2) {
        return entry.getKey();
      }
    }
    return 0;
  }

  public int majorityElement2(int[] nums) {
    /**
     * len = 9, len/2=4
     * 1, 2, 2, 2, 2, 2, 3, 4, 5
     * 2, 2, 2, 2, 2, 3, 4, 5, 6
     * 2, 2, 2, 2, 6, 6, 6, 6, 6
     */
    Arrays.sort(nums);
    return nums[nums.length / 2];
  }

  // 摩尔投票法
  public int majorityElement3(int[] nums) {
    // x为假定的众数，votes是投票数目
    int x = 0, votes = 0;
    for (int num : nums) {
      if (votes == 0) {
        x = num;
      }
      votes += (num == x) ? 1 : -1;
    }
    return x;
  }
}
