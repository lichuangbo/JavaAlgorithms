package top.xiaotian.algorithms.map;

import java.util.HashMap;

/**
 * 560. 和为K的子数组 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。 说明 :
 *
 * 数组的长度为 [1, 20,000]。 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
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
   * 查找表法
   * 时间O(n)
   * 空间O(n)
   */
  public int subarraySum2(int[] nums, int k) {
    int count = 0, pre = 0;
    // pre[i]表示从0到i区间的元素和，假设某一个区间[m,n]的和为k,那么pre[n]-pre[m-1]=k   ===>   pre[n]-k=pre[m-1]
    // 键为pre[n]-k,值为出现次数
    HashMap<Integer, Integer> mp = new HashMap<>();
    mp.put(0, 1);
    for (int i = 0; i < nums.length; i++) {
      pre += nums[i];
      if (mp.containsKey(pre - k)) {
        count += mp.get(pre - k);
      }
      mp.put(pre, mp.getOrDefault(pre, 0) + 1);
    }
    return count;
  }

  public static void main(String[] args) {
    // (1,-1)  (1,-1,0) (0)
    int[] nums = {1, -1, 0};
    int i = new SubarraySum().subarraySum2(nums, 0);
    System.out.println(i);
  }
}
