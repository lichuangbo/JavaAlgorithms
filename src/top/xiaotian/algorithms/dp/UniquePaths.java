package top.xiaotian.algorithms.dp;

import java.util.Arrays;

/**
 * @author lichuangbo
 * @version 1.0
 * @created 2021/2/7
 */
public class UniquePaths {
    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * 问总共有多少条不同的路径？
     * 示例 1：
     * 输入：m = 3, n = 7
     * 输出：28
     */
    public int uniquePaths(int m, int n) {
        /**
         * dp[i][j]表示机器人走到这个位置上可能的路径数
         * dp[i][j] = dp[i-1][j] + dp[i][j-1];
         * 3行2列演示
         * 1 1         1 1
         * 1      ->   1 2
         * 1           1 3
         */
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    // 左+上
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePaths2(int m, int n) {
        /**
         * 3行2列演示
         * 1 1        [1 1]         [1 1]
         *        ->   1 2     ->   [1 2]
         *                           1 3
         * 滚动数组，可以理解为复用了左和上的值
         */
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public int uniquePaths3(int m, int n) {
        return dfs(m - 1, n - 1, 0, 0);
    }

    private int dfs(int m, int n, int i, int j) {
        // 探索到边界
        if (i > m || j > n) {
            return 0;
        }
        if (i == m && j == n) {
            return 1;
        }
        return dfs(m, n, i + 1, j) + dfs(m, n, i, j + 1);
    }

    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
     *
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     *
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        int[][] dp = new int[rows][cols];
        /**
         * 初始化问题：基本思路和路径I一致
         * 但是由于路障的存在，例如初始化首行，中途出现了一个路障，那么路障后边的都应该为0，两种写法是一样的
         */
        for (int i = 0; i < rows && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < cols; j++) {
            if (obstacleGrid[0][j] == 1) {
              break;
            }
            dp[0][j] = 1;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 1) {// 遇到石头，将它标记为0(不对产生别的结果造成影响)
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[rows - 1][cols - 1];
    }

  public static void main(String[] args) {
    new UniquePaths().uniquePathsWithObstacles(new int[][]{
            {0, 1, 0, 1, 0}
    });
  }
}
