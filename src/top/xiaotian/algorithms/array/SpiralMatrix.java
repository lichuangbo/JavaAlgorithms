package top.xiaotian.algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<Integer> res = new ArrayList<>();
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
        while (true) {
            for (int j = l; j <= r; j++) {
                res.add(matrix[t][j]);
            }
            t++;
            if (t > b) {
                break;
            }
            for (int i = t; i <= b; i++) {
                res.add(matrix[i][r]);
            }
            r--;
            if (l > r) {
                break;
            }
            for (int j = r; j >= l; j--) {
                res.add(matrix[b][j]);
            }
            b--;
            if (t > b) {
                break;
            }
            for (int i = b; i >= t; i--) {
                res.add(matrix[i][l]);
            }
            l++;
            if (l > r) {
                break;
            }
        }
        return res;
    }

    // 59. 螺旋矩阵 II
    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;// 分别对应左、右、上、下
        int[][] res = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){
            for(int j = l; j <= r; j++) res[t][j] = num++; // 从左向右填充
            t++;// 缩圈
            for(int i = t; i <= b; i++) res[i][r] = num++; // 从上向下填充
            r--;
            for(int j = r; j >= l; j--) res[b][j] = num++; // 从右向左填充
            b--;
            for(int i = b; i >= t; i--) res[i][l] = num++; // 从下向上填充
            l++;
        }
        return res;
    }
}
