package top.xiaotian.algorithms.dp.path;

/**
 * 剑指 Offer 47. 礼物的最大价值
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，
 * 并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 */
public class MaxValue {
  /**
   * 时间复杂度O(m*n)  空间复杂度O(m*n)
   */
  public int maxValue(int[][] grid) {
    int rows = grid.length, cols = grid[0].length;
    int[][] dp = new int[rows][cols];
    dp[0][0] = grid[0][0];
    /**
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     *
     * [1,4,5],
     * [2,   ],
     * [6,   ]
     *
     * [1,4,5],
     * [2,9,10],
     * [6,11,12]
     */
    // 初始化第一行
    for (int j = 1; j < cols; j++) {
      dp[0][j] = dp[0][j - 1] + grid[0][j];
    }
    // 初始化第一列
    for (int i = 1; i < rows; i++) {
      dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    for (int i = 1; i < rows; i++) {
      for (int j = 1; j < cols; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
      }
    }
    return dp[rows - 1][cols - 1];
  }

  /**
   * 原地dp
   * 时间O(m*n) 空间O(1)
   */
  public int maxValue2(int[][] grid) {
    int rows = grid.length, cols = grid[0].length;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (i == 0 && j == 0) {
          continue;
        }
        if (i == 0) {
          grid[i][j] += grid[i][j - 1];
        } else if (j == 0) {
          grid[i][j] += grid[i - 1][j];
        } else {
          grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
        }
      }
    }
    return grid[rows - 1][cols - 1];
  }
}
