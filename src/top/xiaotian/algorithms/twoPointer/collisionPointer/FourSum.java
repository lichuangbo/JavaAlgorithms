package top.xiaotian.algorithms.twoPointer.collisionPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 *
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c],
 * nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d < n a、b、c 和 d 互不相同 nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * 你可以按 任意顺序 返回答案 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/10
 */
public class FourSum {

  /**
   * 时间O(n3)  在三数之和的基础上添加一重遍历
   */
  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    int length = nums.length;
    for (int i = 0; i < length - 1; i++) {// 固定第一个数
      // [1000000000,1000000000,1000000000,1000000000]  -294967296
      // 用例溢出，在这里进行剪枝
      if (nums[i] > 0 && nums[i] > target) {
        break;
      }
      if (i != 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      for (int j = i + 1; j < length - 1; j++) {// 固定第二个数
        // 去重注意点：是第一个进行计算，后续出现重复的不计算
        // j > 0 && nums[j] == nums[j - 1] 是错误的
        if (j != i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }
        int l = j + 1, r = length - 1;
        while (l < r) {
          long sum = nums[i] + nums[j] + nums[l] + nums[r];
          if (sum == target) {
            res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
            l++;
            r--;

            while (l < r && nums[l] == nums[l - 1]) {
              l++;
            }
            while (r > l && nums[r] == nums[r + 1]) {
              r--;
            }
          } else if (sum < target) {
            l++;
          } else {
            r--;
          }
        }
      }
    }
    return res;
  }
}
