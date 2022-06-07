package top.xiaotian.algorithms.twoPointer.slidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 * <p>
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= target <= 10^5
 */
public class ContinuousSequence {
  public int[][] findContinuousSequence(int target) {
    int left = 1, right = 2;
    List<int[]> resList = new ArrayList<>();
    // 窗口大小必须大于1，题目中要求了
    while (left < right) {
      // 等差数列求和
      int sum = (right - left + 1) * (left + right) / 2;
      if (sum < target) {
        right++;
      } else if (sum > target) {
        left++;
      } else {
        int[] tmp = new int[right - left + 1];
        for (int i = left; i <= right; i++) {
          tmp[i - left] = i;
        }
        resList.add(tmp);
        left++;
      }
    }
    int[][] res = new int[resList.size()][];
    for (int i = 0; i < resList.size(); i++) {
      res[i] = resList.get(i);
    }
    return res;
  }
}
