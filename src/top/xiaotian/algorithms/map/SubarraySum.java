package top.xiaotian.algorithms.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class SubarraySum {

  /**
   * 暴力循环，时间O(n2),空间O(1)
   */
  public int subarraySum(int[] nums, int k) {
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
      int curr = 0;
      for (int j = i; j < nums.length; j++) {
        curr += nums[j];
        if (curr == k) {
          res++;
        }
      }
    }
    return res;
  }

  /**
   * 前缀和+查找表法
   * 时间O(n)
   * 空间O(n)
   */
  public int subarraySum2(int[] nums, int k) {
    int count = 0;
    // pre[i]表示区间[0, i]的元素和，假设某一个区间[m,n]的和为k,那么pre[n]-pre[m-1]=k   ===>   pre[n]-k=pre[m-1]
    int pre = 0;
    // 键为前缀和pre[n],值为出现次数
    Map<Integer, Integer> map = new HashMap<>();

    map.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
      // pre负责求和，也就是前缀和
      pre += nums[i];

      // 如果map包含pre[n]-k，说明找到了一组解，更新count
      if (map.containsKey(pre - k)) {
        count += map.get(pre - k);
      }

      // 更新map中，pre出现的次数
      map.put(pre, map.getOrDefault(pre, 0) + 1);
    }
    return count;
  }

  /**
   * 不可以用双指针/滑动窗口的原因：
   * 因为nums[i]可以小于0，也就是说右指针向后移1位不能保证区间会增大，左指针向后移1位也不能保证区间和会减小
   */
}
