package top.xiaotian.algorithms.array;

import java.util.Arrays;

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
    if (a == null || a.length == 0) {
      return new int[0];
    }

    int[] resArr = new int[a.length];
    // 记录结果集第几个元素
    int k = 0;
    for (int i = 0; i < a.length; i++) {
      int tmpRes = 1;
      for (int j = 0; j < a.length; j++) {
        if (j != k) {
          tmpRes = tmpRes * a[j];
        }
      }
      // 计算到最后一个元素，放入结果集
      resArr[k++] = tmpRes;
    }
    return resArr;
  }

  /**
   * 1 | 1  2  3  4  5  | 120
   * 1 | 1  1  3  4  5  | 60
   * 2 | 1  2  1  4  5  | 20
   * 6 | 1  2  3  1  5  | 5
   *24 | 1  2  3  4  1  | 1
   */
  public int[] constructArr2(int[] a) {
    if (a == null || a.length == 0) {
      return new int[0];
    }

    int[] resArr = new int[a.length];
    resArr[0] = 1;
    // 计算左下三角
    for (int i = 1; i < a.length; i++) {
      resArr[i] = a[i - 1] * resArr[i - 1];
    }
    // 计算右上三角
    int tmpRes = 1;
    for (int i = a.length - 2; i >= 0; i--) {
      tmpRes = tmpRes * a[i + 1];
      // 同时计算结果，放入结果集
      resArr[i] = resArr[i] * tmpRes;
    }

    return resArr;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{1,2,3,4,5};
    int[] ints = new ConstructArr().constructArr2(arr);
    System.out.println(Arrays.toString(ints));
  }
}
