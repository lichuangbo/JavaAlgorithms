package top.xiaotian.algorithms.array;

/**
 * 剑指 Offer 66. 构建乘积数组
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
 * 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 */
public class ConstructArr {

  // 超时
  public int[] constructArr(int[] a) {
    int len = a.length;
    int[] res = new int[len];
    for (int i = 0; i < len; i++) {
      int multi = 1;
      for (int j = 0; j < len; j++) {
        if (i != j) {
          multi *= a[j];
        }
      }
      res[i] = multi;
    }
    return res;
  }

  /**
   * 1 | 1  2  3  4  5  | 120
   * 1 | 1  1  3  4  5  | 60
   * 2 | 1  2  1  4  5  | 20
   * 6 | 1  2  3  1  5  | 5
   *24 | 1  2  3  4  1  | 1
   */
  // 动态规划
  public int[] constructArr2(int[] a) {
    if (a == null || a.length == 0) {
      return a;
    }
    int len = a.length;
    int[] left = new int[len];
    int[] right = new int[len];
    left[0] = right[len - 1] = 1;

    for (int i = 1; i < len; i++) {
      left[i] = left[i - 1] * a[i - 1];
    }
    for (int i = len - 2; i >= 0; i--) {
      right[i] = right[i + 1] * a[i + 1];
    }

    int[] res = new int[len];
    for (int i = 0; i < len; i++) {
      res[i] = left[i] * right[i];
    }
    return res;
  }

  // 状态压缩
  public int[] constructArr3(int[] a) {
    if (a == null || a.length == 0) {
      return new int[0];
    }

    int len = a.length;
    int[] res = new int[len];
    res[0] = 1;
    // 计算左下三角
    for (int i = 1; i < len; i++) {
      res[i] = a[i - 1] * res[i - 1];
    }
    // 计算右上三角
    int tmpRes = 1;
    for (int i = len - 2; i >= 0; i--) {
      tmpRes = tmpRes * a[i + 1];
      // 同时计算结果，放入结果集
      res[i] = res[i] * tmpRes;
    }

    return res;
  }
}
