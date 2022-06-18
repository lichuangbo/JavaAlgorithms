package top.xiaotian.algorithms.binarySearch;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= n <= 1000
 * <p>
 * 0 <= m <= 1000
 */
public class NumberIn2DArray {
  /**
   * 1 4 7               7
   * 2 5 8             4   8
   * 3 6 9          1    5    9
   *                   2   6
   *                     3
   */
  public boolean findNumberIn2DArray(int[][] matrix, int target) {
    // 以左下角节点为根
    int i = matrix.length - 1, j = 0;
    while (i >= 0 && j <= matrix[i].length - 1) {
      // 当前值小于目标值（如3<6），只可能在当前位置的右边
      if (matrix[i][j] < target) {
        j++;
        // 当前值大于目标值（如3>2），只可能在当前位置的上边
      } else if (matrix[i][j] > target) {
        i--;
      } else {
        return true;
      }
    }
    return false;
  }

  public boolean findNumberIn2DArray2(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }
    // 以右上角节点为根
    int i = 0, j = matrix[0].length - 1;
    while (i < matrix.length && j >= 0) {
      // 当前值小于目标值（如7<8），只可能在当前位置的下边
      if (matrix[i][j] < target) {
        i++;
        // 当前值大于目标值（如7>4），只可能在当前位置的左边
      } else if (matrix[i][j] > target) {
        j--;
      } else {
        return true;
      }
    }
    return false;
  }
}
