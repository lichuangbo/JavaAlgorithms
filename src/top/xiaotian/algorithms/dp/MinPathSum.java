package top.xiaotian.algorithms.dp;

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
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        /**
         * 1 3 1  ^    (2,0)  ->  (0,2)
         * 1 5 1  |    (1,0)  ->  (1,2)
         * 4 2 1  i    (0,0)  ->  (2,2)
         *   <-j
         *
         * 0 0 3
         * 0 0 2 i
         * 7 3 1    这里和三角形问题不同，不能笼统的取最小，必须先将最右列和最下行初始化好
         *   j
         */
        dp[0][0] = grid[rows - 1][cols - 1];
        // 初始化最右列
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[rows - i - 1][cols - 1];
        }
        // 初始化最下行
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[rows - 1][cols - j - 1];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                int right = dp[i - 1][j];
                int down = dp[i][j - 1];
                dp[i][j] = Math.min(down, right) + grid[rows - i - 1][cols - j - 1];
            }
        }
        return dp[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int res = new MinPathSum().minPathSum(grid);
        System.out.println(res);
    }
}
