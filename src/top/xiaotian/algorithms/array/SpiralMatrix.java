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
   *
   * 示例 1：
   *
   * 输入：n = 3
   * 输出：[[1,2,3],[8,9,4],[7,6,5]]
   */
  public int[][] generateMatrix(int n) {
    int l = 0, r = n - 1, t = 0, b = n - 1;// 分别对应左、右、上、下
    int[][] res = new int[n][n];
    int num = 1, tar = n * n;
    while (num <= tar) {
      for (int j = l; j <= r; j++) res[t][j] = num++; // 从左向右填充
      t++;// 缩圈
      for (int i = t; i <= b; i++) res[i][r] = num++; // 从上向下填充
      r--;
      for (int j = r; j >= l; j--) res[b][j] = num++; // 从右向左填充
      b--;
      for (int i = b; i >= t; i--) res[i][l] = num++; // 从下向上填充
      l++;
    }
    return res;
  }

  /**
   * 54. 螺旋矩阵
   * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
   *
   * 示例 1：
   *
   * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
   * 输出：[1,2,3,6,9,8,7,4,5]
   * 示例 2：
   *
   * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
   * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
   */
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;
    int num = 1;
    int target = (r + 1) * (b + 1);
    while (true) {
      for (int j = l; j <= r; j++) {
        res.add(matrix[t][j]);
        num++;
      }
      t++;
      if (num > target) {
        break;
      }
      for (int i = t; i <= b; i++) {
        res.add(matrix[i][r]);
        num++;
      }
      r--;
      if (num > target) {
        break;
      }
      for (int j = r; j >= l; j--) {
        res.add(matrix[b][j]);
        num++;
      }
      b--;
      if (num > target) {
        break;
      }
      for (int i = b; i >= t; i--) {
        res.add(matrix[i][l]);
        num++;
      }
      l++;
      if (num > target) {
        break;
      }
    }
    return res;
  }
}
