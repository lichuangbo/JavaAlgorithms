package top.xiaotian.algorithms.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],
 *               [4,5,6],
 *               [7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 1, 2     一横：rows = 1
 * 1        一横一竖: rows > 1 && cols > 1
 * 4
 * 1, 2     一横一竖再一横: rows > 1 && cols > 1
 * 4, 5
 * 1, 2     一横一竖再一横再一竖: rows > 2 && cols > 1
 * 4, 5
 * 7, 8
 * 1, 2, 3  第一圈(0, 0)
 * 4, 5, 6  第二圈(1, 1)  3 > 1 * 2 && 3 > 1 * 2
 * 7, 8, 9
 * 1, 2, 3, 4           m[stRow][0...3]  enCol = cols - stCol- 1   stRow=0, stCol=0
 * 5, 6, 7, 7           m[1...3][enCol]  enRow = rows - stRow - 1
 * 9, 9, 9, 9           m[enRow][2...0]
 * 1, 1, 1, 1           m[2...1][stCol]
 * 1        一横一竖: enRow > stRow
 * 4
 * 1, 2     一横一竖再一横: enRow > stRow && enCol > stCol
 * 4, 5
 * 1, 2     一横一竖再一横再一竖: enRow - 1 > stRow && enCol > stCol
 * 4, 5
 * 7, 8
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/3/15 15:05
 * @Description: 描述:
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }

        int rows = matrix.length, cols = matrix[0].length;
        int stRow = 0, stCol = 0;
        while (rows >= stRow * 2 && cols >= stCol * 2) {
            int enRow = rows - stRow - 1;
            int enCol = cols - stCol - 1;
            for (int j = stCol; j <= enCol; j++) {
                res.add(matrix[stRow][j]);
            }
            if (enRow > stRow) {
                for (int i = stRow + 1; i <= enRow; i++) {
                    res.add(matrix[i][enCol]);
                }
            }
            if (enRow > stRow && enCol > stCol) {
                for (int j = enCol - 1; j >= stCol; j--) {
                    res.add(matrix[enRow][j]);
                }
            }
            if (enRow - 1 > stRow && enCol > stCol) {
                for (int i = enRow - 1; i >= stRow + 1; i--) {
                    res.add(matrix[i][stCol]);
                }
            }
            stRow++; stCol++;
        }
        return res;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int total = rows * cols;
        int row = 0, col = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;// 记录当前方向，0-右，1-下，2-左，3-上， 循环使用
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][col]);
            visited[row][col] = true;
            int nextRow = row + directions[directionIndex][0], nextCol = col + directions[directionIndex][1];
            // 按照当前方向继续访问  如果出现访问越界 或者 元素已经访问过 就更换方向
            if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || visited[nextRow][nextCol]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            // 按照当前方向前进
            row += directions[directionIndex][0];
            col += directions[directionIndex][1];
        }
        return order;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        List<Integer> res = new SpiralMatrix().spiralOrder2(matrix);
        System.out.println(res);
    }
}
