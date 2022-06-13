package top.xiaotian.algorithms.sort.practice;

import java.util.Arrays;

/**
 * 剑指 Offer 45. 把数组排成最小的数
 *
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [10,2] 输出: "102"
 *
 * 示例 2:
 *
 * 输入: [3,30,34,5,9] 输出: "3033459"
 *
 *
 *
 * 提示:
 *
 * 0 < nums.length <= 100
 *
 * 说明:
 *
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 */
public class MinNumber {

  /**
   * n1 + n2 < n2 + n1, 那么n1应该排在n2的左侧
   * 30 + 3  < 3 + 30,说明30应该排在3的左侧
   */
  public String minNumber(int[] nums) {
    int len = nums.length;
    String[] strs = new String[len];
    for (int i = 0; i < len; i++) {
      strs[i] = String.valueOf(nums[i]);
    }

    Arrays.sort(strs, (n1, n2) -> (n1 + n2).compareTo(n2 + n1));
    StringBuilder sb = new StringBuilder();
    for (String str : strs) {
      sb.append(str);
    }
    return sb.toString();
  }
}
