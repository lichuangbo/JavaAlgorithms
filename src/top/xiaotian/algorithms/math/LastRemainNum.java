package top.xiaotian.algorithms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 *
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 *
 *
 * 示例 1：
 *
 * 输入: n = 5, m = 3
 * 输出: 3
 *
 * 示例 2：
 *
 * 输入: n = 10, m = 17
 * 输出: 2
 */
public class LastRemainNum {
  public int lastRemaining(int n, int m) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      list.add(i);
    }

    // 第一次删除的下标为idx，那么下一次删除的下标为(idx+m)%n, 但是存在删除n会减少，所以下一次删除的下标(idx+m-1)%n
    int idx = 0;
    while (n > 1) {
      idx = (idx + m - 1) % n;
      Integer remove = list.remove(idx);
      System.out.println(remove);
      n--;
    }
    return list.get(0);
  }

  public int lastRemaining2(int n, int m) {
    // 状态定义：dp[i]存储的是[i...m]问题的结果
    // 状态方程：dp[i] = (dp[i - 1] + m) % i
    int[] dp = new int[n + 1];
    dp[1] = 0;
    for (int i = 2; i <= n; i++) {
      dp[i] = (dp[i - 1] + m) % i;
    }
    return dp[n];
  }

  public int lastRemaining3(int n, int m) {
    // 状态压缩
    int res = 0;
    for (int i = 2; i <= n; i++) {
      res = (res + m) % i;
    }
    return res;
  }
}
