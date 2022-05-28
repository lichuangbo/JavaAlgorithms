package top.xiaotian.algorithms.math;

/**
 * 剑指 Offer 65. 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: a = 1, b = 1
 * 输出: 2
 * <p>
 * <p>
 * 提示：
 * <p>
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 */
public class Add {
  /**
   * a(i)    b(i)    无进位和n(i)     进位c(i)
   * 0        0          0              0
   * 0        1          1              0
   * 1        0          1              0
   * 1        1          0              1
   * a + b = n + c
   * n是异或运算结果
   * c是与运算结果，左移一位
   * 当进位为0时，结束运算
   */
  public int add(int a, int b) {
    if (b == 0) {
      return a;
    }

    // 转换成非进位和 + 进位
    return add(a ^ b, (a & b) << 1);
  }

  public int add2(int a, int b) {
    while (b != 0) { // 当进位为 0 时跳出
      int c = (a & b) << 1;  // c = 进位
      a = a ^ b; // a = 非进位和
      b = c; // b = 进位
    }
    return a;
  }
}
