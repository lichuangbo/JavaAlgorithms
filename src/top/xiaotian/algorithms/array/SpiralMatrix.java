package top.xiaotian.algorithms.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵问题
 */
public class SpiralMatrix {

    /**
     * 59. 螺旋矩阵 II
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：n = 3
     * 输出：[[1,2,3],[8,9,4],[7,6,5]]
     * 示例 2：
     * <p>
     * 输入：n = 1
     * 输出：[[1]]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 20
     */
    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int num = 1;
        int[][] res = new int[n][n];
        while (num <= n * n) {
            for (int j = l; j <= r; j++) {// 左到右
                res[t][j] = num++;
            }
            t++;
            for (int i = t; i <= b; i++) {// 上到下
                res[i][r] = num++;
            }
            r--;
            for (int j = r; j >= l; j--) {// 右到左
                res[b][j] = num++;
            }
            b--;
            for (int i = b; i >= t; i--) {// 下到上
                res[i][l] = num++;
            }
            l++;
        }
        return res;
    }

    /**
     * 54. 螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     * <p>
     * <p>
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     * <p>
     * <p>
     * 提示：
     * <p>
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 10
     * -100 <= matrix[i][j] <= 100
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int l = 0, r = cols - 1, t = 0, b = rows - 1;
        List<Integer> res = new ArrayList<>();
        int total = rows * cols;
        while (res.size() != total) {
            for (int j = l; j <= r; j++) {
                res.add(matrix[t][j]);
            }
            if (res.size() == total) break;
            t++;
            for (int i = t; i <= b; i++) {
                res.add(matrix[i][r]);
            }
            if (res.size() == total) break;
            r--;
            for (int j = r; j >= l; j--) {
                res.add(matrix[b][j]);
            }
            if (res.size() == total) break;
            b--;
            for (int i = b; i >= t; i--) {
                res.add(matrix[i][l]);
            }
            if (res.size() == total) break;
            l++;
        }
        return res;
    }
}
