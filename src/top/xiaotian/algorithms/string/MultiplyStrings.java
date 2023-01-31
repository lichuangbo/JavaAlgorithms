package top.xiaotian.algorithms.string;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 */
public class MultiplyStrings {

  // 模拟人工计算
  public String multiply(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }
    // 保存计算结果
    String res = "0";

    char[] chars1 = num1.toCharArray();
    char[] chars2 = num2.toCharArray();
    int len1 = chars1.length;
    int len2 = chars2.length;
    // num2 逐位与 num1 相乘
    for (int i = len2 - 1; i >= 0; i--) {
      int carry = 0;
      // 保存 num2 第i位数字与 num1 相乘的结果
      StringBuilder temp = new StringBuilder();
      // 补 0
      for (int j = 0; j < len2 - 1 - i; j++) {
        temp.append(0);
      }
      // num2 的第 i 位数字 n2 与 num1 相乘
      int n2 = chars2[i] - '0';
      for (int j = len1 - 1; j >= 0 || carry != 0; j--) {
        int n1 = j < 0 ? 0 : chars1[j] - '0';
        int product = (n1 * n2 + carry) % 10;
        temp.append(product);
        carry = (n1 * n2 + carry) / 10;
      }
      // 将当前结果与新计算的结果求和作为新的结果
      res = addStrings(res, temp.reverse().toString());
    }
    return res;
  }

  // 找规律
  public String multiply2(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) {
      return "0";
    }
    char[] chars1 = num1.toCharArray();
    char[] chars2 = num2.toCharArray();
    int len1 = chars1.length;
    int len2 = chars2.length;
    int[] res = new int[len1 + len2];
    for (int i = len1 - 1; i >= 0; i--) {
      int n1 = chars1[i] - '0';
      for (int j = len2 - 1; j >= 0; j--) {
        int n2 = chars2[j] - '0';
        int sum = res[i + j + 1] + n1 * n2;
        res[i + j + 1] = sum % 10;
        res[i + j] += sum / 10;
      }
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < res.length; i++) {
      if (i == 0 && res[i] == 0) {
        continue;
      }
      result.append(res[i]);
    }
    return result.toString();
  }

  /**
   * 415. 字符串相加
   * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
   *
   * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
   *
   *
   *
   * 示例 1：
   *
   * 输入：num1 = "11", num2 = "123"
   * 输出："134"
   * 示例 2：
   *
   * 输入：num1 = "456", num2 = "77"
   * 输出："533"
   * 示例 3：
   *
   * 输入：num1 = "0", num2 = "0"
   * 输出："0"
   */
  public String addStrings(String num1, String num2) {
    char[] chars1 = num1.toCharArray();
    char[] chars2 = num2.toCharArray();
    int len1 = num1.length();
    int len2 = num2.length();

    StringBuilder sb = new StringBuilder();
    int carry = 0;
    int i = len1 - 1;
    int j = len2 - 1;
    while (i >= 0 || j >= 0 || carry != 0) {
      int x = i < 0 ? 0 : chars1[i] - '0';
      int y = j < 0 ? 0 : chars2[j] - '0';
      int sum = (x + y + carry) % 10;
      sb.append(sum);
      carry = (x + y + carry) / 10;

      i--;
      j--;
    }
    return sb.reverse().toString();
  }
}
