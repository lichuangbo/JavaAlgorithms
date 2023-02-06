package top.xiaotian.algorithms.sort.practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 61. 扑克牌中的顺子
 *
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为
 * 14。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5] 输出: True
 *
 *
 * 示例 2:
 *
 * 输入: [0,0,1,2,5] 输出: True 1 2 (3 4) 5 限制：
 *
 * 数组长度为 5
 *
 * 数组的数取值为 [0, 13] .
 */
public class Straight {

  public boolean isStraight(int[] nums) {
    Arrays.sort(nums);
    int gap = 0;// 除去大小王外牌间隙
    int zeroNum = 0;// 大小王数量
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == 0) {
        zeroNum++;
        continue;
      }

      // 除大小王外，一旦出现牌相等，必然不能组成对子
      if (nums[i] == nums[i + 1]) {
        return false;
      }
      // 统计整副牌的间隙
      gap += nums[i + 1] - nums[i] - 1;
    }
    // 间隙为0（如1,2,3,4,5），间隙不为0那么间隙必须比大小王数量小（如0,0,1,2,5）  注意特例11,0,9,0,0
    if (gap == 0 || gap <= zeroNum) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isStraight2(int[] nums) {
    // 除大小王外，最大牌-最小牌<5,就能构成对子
    int min = nums[0];
    int max = nums[0];
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        continue;
      }
      max = Math.max(max, nums[i]);
      min = Math.min(min, nums[i]);
      if (set.contains(nums[i])) {
        return false;
      }
      set.add(nums[i]);
    }
    return max - min < 5;
  }

  public boolean isStraight3(int[] nums) {
    Arrays.sort(nums);
    int num0 = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == 0) {
        num0++; // 统计大小王数量
      }// 除大小王外，一旦出现牌相等，必然不能组成对子
      else if (nums[i] == nums[i + 1]) {
        return false;
      }
    }
    return nums[4] - nums[num0] < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
  }

}
