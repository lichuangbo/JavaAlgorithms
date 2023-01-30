package top.xiaotian.algorithms.math;

/**
 * 168. Excel表列名称
 *
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 *
 * 例如：
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 *
 *
 * 示例 1：
 *
 * 输入：columnNumber = 1
 * 输出："A"
 *
 * 示例 2：
 *
 * 输入：columnNumber = 28
 * 输出："AB"
 *
 * 示例 3：
 *
 * 输入：columnNumber = 701
 * 输出："ZY"
 *
 * 示例 4：
 *
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 * @author lichuangbo
 * @date 2023/1/30
 */
public class ExcelSheetColumnTitle {

  // 26进制升级版：正儿八经的26进制范围是[0, 26),但是excel是[1, 26],所以要减一修正偏移
  public String convertToTitle(int cn) {
    StringBuilder sb = new StringBuilder();
    while (cn > 0) {
      cn--;
      sb.append((char) (cn % 26 + 'A'));
//      sb.append((char) (cn % 26 + 65));
      cn /= 26;
    }
    sb.reverse();
    return sb.toString();
  }
}
