package top.xiaotian.algorithms.dp;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 *
 *
 * 示例 1：
 *
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/6
 */
public class Triangle {

  // 自下而上
  public int minimumTotal(List<List<Integer>> triangle) {
    int len = triangle.size();
    int[][] dp = new int[len + 1][len + 1];
    /**
     *  0 2
     *^ 0 3 4
     *| 0 6 5 7
     *i 0 4 1 8 3
     *  0 0 0 0 0
     *  j ->
     *
     *  11
     *  9  10
     *  7  6  10
     *  4  1  8  3
     *  0  0  0  0  0
     */
    for (int i = len - 1; i >= 0; i--) {
      for (int j = 0; j <= i; j++) {
        dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
      }
    }
    return dp[0][0];
  }

  // 自下而上，状态压缩
  public int minimumTotal2(List<List<Integer>> triangle) {
    int len = triangle.size();
    int[] dp = new int[len];
    // 初始化：最后一行的最小值时自身
    for (int j = 0; j < len; j++) {
      dp[j] = triangle.get(len - 1).get(j);
    }

    for (int i = len - 2; i >= 0; i--) {
      for (int j = 0; j <= i; j++) {
        dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
      }
    }
    return dp[0];
  }

  // 自上而下
  public int minimumTotal3(List<List<Integer>> triangle) {
    int len = triangle.size();
    int[][] dp = new int[len][len];
    /**
     *  2
     *  3 4
     *  6 5 7
     *  4 1 8 3
     *
     *  2
     *  5  6
     *  11 10 13
     *  15 11 18 16
     */
    // 初始化：三角形左侧只能通过上一层得到
    dp[0][0] = triangle.get(0).get(0);
    // dp推演
    for (int i = 1; i < len; i++) {
      // j==0时，只能从正上方得到（如3只能从2过来）
      dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
      // j在(0, i)区间时，可以从两个地方过来
      for (int j = 1; j < i; j++) {
        dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
      }
      // j==i时，只能从上一层的末尾得到（如4只能从2过来）
      dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
    }

    int min = dp[len - 1][0];
    for (int j = 1; j < len; j++) {
      min = Math.min(min, dp[len - 1][j]);
    }
    return min;
  }
}
