package top.xiaotian.algorithms.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 * <p>
 * 输入: n = 10, m = 17
 * 输出: 2
 */
public class LastRemainNum {
  public int lastRemaining(int n, int m) {
    List<Integer> list = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      list.add(i);
    }

    // 当前删除的位置是idx，下一个删除的位置应该是idx + m，由于移除了一个需要同步向前移idx + m - 1
    int idx = 0;
    while (n > 1) {
      idx = (idx + m - 1) % n;
      list.remove(idx);
      n--;
    }
    return list.get(0);
  }

  public int lastRemaining2(int n, int m) {
    int x = 0;
    for (int i = 2; i <= n; i++) {
      x = (x + m) % i;
    }
    return x;
  }

}
