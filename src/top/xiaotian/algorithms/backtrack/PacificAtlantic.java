package top.xiaotian.algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题
 * 给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。
 *
 * 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。
 *
 * 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。
 *
 * 提示：
 *
 * 输出坐标的顺序不重要
 * m 和 n 都小于150
 *
 * 示例：
 * 给定下面的 5x5 矩阵:
 *
 *   太平洋 ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * 大西洋
 *
 * 返回:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
 * @author lichuangbo
 * @version 1.0
 * @created 2021/1/31
 */
public class PacificAtlantic {
    private int rows;
    private int cols;
    private boolean[][] pacificVisited;
    private boolean[][] atlanticVisited;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }

        rows = matrix.length;
        cols = matrix[0].length;
        pacificVisited = new boolean[rows][cols];
        atlanticVisited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            help(matrix, pacificVisited, i, 0, matrix[i][0]);// 第一列
            help(matrix, atlanticVisited, i, cols - 1, matrix[i][cols - 1]);// 最后一列
        }
        for (int j = 0; j < cols; j++) {
            help(matrix, pacificVisited, 0, j, matrix[0][j]);// 第一行
            help(matrix, atlanticVisited,rows - 1, j, matrix[rows - 1][j]);// 最后一行
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    // 语义：将大于matrix边界上的元素标记
    private void help(int[][] matrix, boolean[][] visited, int i, int j, int preValue) {
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        if (matrix[i][j] < preValue) {
            return;
        }

        visited[i][j] = true;
        help(matrix, visited, i, j + 1, matrix[i][j]);// 右
        help(matrix, visited, i + 1, j, matrix[i][j]);// 下
        help(matrix, visited, i, j - 1, matrix[i][j]);// 左
        help(matrix, visited, i - 1, j, matrix[i][j]);// 上
    }
}
