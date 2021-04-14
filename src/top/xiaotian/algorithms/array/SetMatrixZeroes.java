package top.xiaotian.algorithms.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 73. 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * @author lichuangbo
 * @version 1.0
 * @created 2021/3/21
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        Set<Integer> row = new HashSet<>(), col = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (row.contains(i) || col.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroes2(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean flagRow = false, flagCol = false;
        // 判断第一行是否有0
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) {
                flagRow = true;
            }
        }
        // 判断第一列是否有0
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                flagCol = true;
            }
        }
        // 使用第一行、第一列记录该行该列是否有0
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 填充除第一行，第一列外的0
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 填充第一行0
        for (int j = 0; j < cols; j++) {
            if (flagRow) {
                matrix[0][j] = 0;
            }
        }
        // 填充第一列0
        for (int i = 0; i < rows; i++) {
            if (flagCol) {
                matrix[i][0] = 0;
            }
        }
    }
}
