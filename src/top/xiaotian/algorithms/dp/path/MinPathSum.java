package top.xiaotian.algorithms.dp.path;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/6
 */
public class MinPathSum {

  public int minPathSum(int[][] grid) {
    /**
     * 1 3 1
     * 1 5 1
     * 4 2 1
     *
     * 1 4 5
     * 2 7 6
     * 6 8 7
     */
    int m = grid.length;
    int n = grid[0].length;
    int[][] dp = new int[m][n];
    // 初始化: 第一行只能从它左侧过来，第一列只能从它正上方过来
    dp[0][0] = grid[0][0];
    for (int i = 1; i < m; i++) {
      dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    for (int j = 1; j < n; j++) {
      dp[0][j] = dp[0][j - 1] + grid[0][j];
    }
    // dp推演
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
      }
    }
    return dp[m - 1][n - 1];
  }

}
