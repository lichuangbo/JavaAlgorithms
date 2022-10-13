package top.xiaotian.algorithms.array;

/**
 * 面试题 01.07. 旋转矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * <p>
 * 不占用额外内存空间能否做到？
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * <p>
 * [1,4,7]
 * [2,5,8]
 * [3,6,9]
 */
public class RotateImage {

  public void rotate(int[][] matrix) {
    int len = matrix.length;
    // 以对角线对称过去, 记住这个倒置数组的写法
    for (int i = 0; i < len - 1; i++) {
      for (int j = i + 1; j < len; j++) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = tmp;
      }
    }
    // 矩阵每一行先翻转顺序
    for (int i = 0; i < len; i++) {
      int m = 0;
      int n = len - 1;
      while (m < n) {
        int tmp = matrix[i][m];
        matrix[i][m] = matrix[i][n];
        matrix[i][n] = tmp;

        m++;
        n--;
      }
    }
  }

  public void rotate2(int[][] matrix) {
    /**
     * 假设左上角区域坐标为(i,j)
     * 其余区域坐标顺时针依次为(j,n-i-1) (n-i-1,n-j-1) (n-j-1,i)
     */
    int len = matrix.length;
    // 旋转区域最大宽度，4*4需要枚举的是2*2（n^2 / 4=n/2 * n/2）个格子，5*5需要枚举的是3*2（(n^2 - 1)/4=(n+1)/2 * (n-1)/2）的格子
    for (int i = 0; i < len / 2; i++) {
      for (int j = 0; j < (len + 1) / 2; j++) {// 理不清，可以带入4和5进去
        // 矩阵元素旋转
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[len - j - 1][i];
        matrix[len - j - 1][i] = matrix[len - i - 1][len - j - 1];
        matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1];
        matrix[j][len - i - 1] = tmp;
      }
    }
  }
}
