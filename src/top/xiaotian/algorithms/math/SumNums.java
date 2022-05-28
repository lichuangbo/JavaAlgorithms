package top.xiaotian.algorithms.math;

/**
 * 剑指 Offer 64. 求1+2+…+n
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 * <p>
 * 输入: n = 9
 * 输出: 45
 */
public class SumNums {
  // 不符合题意
  public int sumNums(int n) {
    if (n == 1) {
      return 1;
    }

    return n + sumNums(n - 1);
  }

  // 利用逻辑短路
  public int sumNums2(int n) {
    boolean x = n > 1 && (n += sumNums2(n - 1)) > 0;
    System.out.println(n);
    return n;
  }
}
