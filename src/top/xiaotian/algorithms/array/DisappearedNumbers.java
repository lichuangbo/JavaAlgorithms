package top.xiaotian.algorithms.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 448. 找到所有数组中消失的数字
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1], n=8
 * 输出：[5,6]
 * 示例 2：
 *
 * 输入：nums = [1,1]
 * 输出：[2]
 */
public class DisappearedNumbers {
  public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> res = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (!set.contains(num)) {
        set.add(num);
      }
    }
    for (int i = 1; i <= nums.length; i++) {
      if (!set.contains(i)) {
        res.add(i);
      }
    }
    return res;
  }

  /***
   * 时间：O(n),记录元素出现频次O(n),从频次数组中拿结果O(n)
   * 空间：0(n),频次数组
   */
  public List<Integer> findDisappearedNumbers2(int[] nums) {
    List<Integer> res = new ArrayList<>();
    int[] freq = new int[nums.length + 1];
    for (int num : nums) {
      freq[num]++;
    }
    for (int i = 1; i < freq.length; i++) {
      if (freq[i] == 0) {
        res.add(i);
      }
    }
    return res;
  }

  /**
   * 时间：O(n)
   * 空间：O(1)
   */
  public List<Integer> findDisappearedNumbers3(int[] nums) {
    int n = nums.length;
    // 
    for (int num : nums) {
      int x = (num - 1) % n;
      nums[x] += n;
    }
    List<Integer> ret = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (nums[i] <= n) {
        ret.add(i + 1);
      }
    }
    return ret;
  }
}
