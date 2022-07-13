package top.xiaotian.algorithms.binarySearch;

/**
 * 69. x 的平方根
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 4
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= x <= 231 - 1
 */
public class MySqrt {
  public int mySqrt(int x) {
    int left = 1;
    int right = x;
    int res = 0;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      // mid * mid = x，这种写法在x比较大的时候会发生溢出（+和*最好转化为-和/）
      if (mid == x / mid) {
        return mid;
      } else if (mid > x / mid) {
        right = mid - 1;
      } else {
        left = mid + 1;
        // 核心点，记录左移
        res = mid;
      }
    }
    return res;
  }
}
