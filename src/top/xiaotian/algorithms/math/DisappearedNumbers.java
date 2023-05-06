package top.xiaotian.algorithms.math;

import java.util.*;

/**
 * 448. 找到所有数组中消失的数字
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 示例 2：
 *
 * 输入：nums = [1,1]
 * 输出：[2]
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 */
public class DisappearedNumbers {
  /**
   * 时间O(n)
   * 空间O(n)
   */
  public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> res = new ArrayList<>();
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
        set.add(num);
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
    // len最大是105，使用频次数组统计是OK的
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
    List<Integer> res = new ArrayList<>();
    /**
     * 使用数组的下标来标记数字的出现于否，通过一遍遍历即可标记出全部已经出现的数组
     * [4,3,2,7,8,2,3,1] 初始数据
     *
     * [4,3,2,-7,8,2,3,1] 第一个数据 4 出现，将数组的第四个也就是下标 3 的数据修改为负数。-7 计算时，通过绝对值处理一下即可不影响数据的计算
     * ...
     * [-4,-3,-2,-7,8,2,-3,-1]  数组的第五个，第六个依然为正数，证明 5,6 没有出现
     */
    for (int i = 0; i < nums.length; i++) {
      int index = Math.abs(nums[i]) - 1;
      if (nums[index] > 0) {
        nums[index] = -nums[index];
      }
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) {
        res.add(i + 1);
      }
    }
    return res;
  }
}
