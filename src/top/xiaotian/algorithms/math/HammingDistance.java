package top.xiaotian.algorithms.math;

/**
 * 461. 汉明距离
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 * 示例 1：
 *
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 */
public class HammingDistance {

  /**
   * 时间：O(C)
   * 空间：O(1)
   */
  public int hammingDistance(int x, int y) {
    int res = 0;
    for (int i = 0; i < 32; i++) {
      // 计算最后一位
      int m = x & 1;
      int n = y & 1;
      // 右移
      x = x >> 1;
      y = y >> 1;
      // 判断是否相等
      res += ((m ^ n) == 0 ) ? 0 : 1;
    }
    return res;
  }
}
