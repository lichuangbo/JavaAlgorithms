package top.xiaotian.algorithms.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,2,3]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class MajorityElement {

  /**
   * 时间O(n)
   * 空间O(n)
   */
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

  /**
   * 时间O(nlogn)
   * 空间O(1)
   */
  public int majorityElement2(int[] nums) {
    /**
     * 入参数组比较苛刻，数组一定存在多数元素（大于n/2），排序完之后中间元素一定是那个多数元素
     * 奇数：3 2 3
     * 偶数：1 4 4 4
     */
    Arrays.sort(nums);
    return nums[nums.length / 2];
  }

  /**
   * 时间O(n)
   * 空间O(1)
   */
  public int majorityElement3(int[] nums) {
    /**
     * 摩尔投票法：元素相同，票数+1；元素不同，票数-1，两两相抵。
     * 多数元素的个数 > n/2，其余元素的个数总和 <= n/2
     * 因此多数元素的个数 - 其余元素的个数总和 的结果 肯定 >= 1，也就是抵消完之后就是多数元素
     */
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
