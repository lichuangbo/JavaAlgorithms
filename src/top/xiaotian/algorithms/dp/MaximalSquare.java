package top.xiaotian.algorithms.dp;

/**
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 *
 *
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalSquare {
    /**
     * 时间O(m*n)
     * 空间O(m*n)
     */
    public int maximalSquare(char[][] matrix) {
        /**
         * 原始矩阵       dp值
         * 0 1 1 1 0    0 1 1 1 0
         * 1 1 1 1 0    1 1 2 2 0
         * 0 1 1 1 1    0 1 2 3 1
         * 0 1 1 1 1    0 1 2 3 2
         * 0 0 1 1 1    0 0 1 2 3
         */
        int rows = matrix.length;
        int cols = matrix[0].length;
        // dp[i][j]表示以matrix[i][j]为右下角的正方形，最大边长
        int[][] dp = new int[rows][cols];
        int maxSide = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {// 初始化，第一行第一列如果是1就可以组成一个边长为1的正方形
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }
}
