package top.xiaotian.algorithms.math;

/**
 * 剑指 Offer 15. 二进制中1的个数
 * 191. 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
 *
 *
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用 二进制补码 记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 *
 *
 * 示例 1：
 *
 * 输入：n = 11 (控制台输入 00000000000000000000000000001011)
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 */
public class HammingWeight {
  /**
   * 时间复杂度O(1)
   */
  public int hammingWeight(int n) {
    int count1 = 0;
    for (int i = 0; i < 32; i++) {
      // n & 1可以得到数字的最后一个二进制位
      if ((n & 1) == 1) {
        count1++;
      }
      // 这里只循环了32次，没有涉及负数的带符号右移和不带符号右移，用哪个都可以的
      n = n >> 1;
    }
    return count1;
  }

  public int hammingWeight2(int n) {
    int count1 = 0;
    while (n != 0) {
      if ((n & 1) == 1) {
        count1++;
      }
      // 无符号右移，负数才能进入n==0，退出循环
      n = n >>> 1;
    }
    return count1;
  }

  /**
   * 时间复杂度O(n)  n是1个个数
   */
  public int hammingWeight3(int n) {
    int count1 = 0;
    while (n != 0) {
      count1++;
      /**
       * (n-1)    二进制数字n最右边的1变成0，此1右边的0都变成1
       * n&(n-1)  二进制数字n最右边的1变成0，其余位置不变
       */
      n = n & (n - 1);
    }
    return count1;
  }
}
