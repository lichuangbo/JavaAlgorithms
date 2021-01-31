package top.xiaotian.algorithms.backtrack;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/31
 */
public class LandNum {
    private boolean[][] visited;
    private int rows;
    private int cols;

    public int numIslands(char[][] grid) {
        int res = 0;
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    res ++;
                    fill(grid, i, j);
                }
            }
        }
        return res;
    }

    private void fill(char[][] grid, int i, int j) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            return;
        }
        if (visited[i][j] || grid[i][j] == '0') {
            return;
        }

        visited[i][j] = true;
        fill(grid, i, j + 1);// 右
        fill(grid, i + 1, j);// 下
        fill(grid, i, j - 1);// 左
        fill(grid, i - 1, j);// 上
    }
}
