package top.xiaotian.algorithms.twoPointer.collisionPointer;


import java.util.Stack;

/**
 * 844. 比较含退格的字符串
 * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
 * <p>
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ab#c", t = "ad#c"
 * 输出：true
 * 解释：s 和 t 都会变成 "ac"。
 * 示例 2：
 * <p>
 * 输入：s = "ab##", t = "c#d#"
 * 输出：true
 * 解释：s 和 t 都会变成 ""。
 * 示例 3：
 * <p>
 * 输入：s = "a#c", t = "b"
 * 输出：false
 * 解释：s 会变成 "c"，但 t 仍然是 "b"。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length, t.length <= 200
 * s 和 t 只含有小写字母以及字符 '#'
 */
public class BackspaceCompare {
  // 使用实际的栈模拟问题
  public boolean backspaceCompare(String s, String t) {
    return help(s).equals(help(t));
  }

  private String help(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '#') {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else {
        stack.push(s.charAt(i));
      }
    }
    StringBuilder sb = new StringBuilder();
    for (Character character : stack) {
      sb.append(character);
    }
    return sb.toString();
  }

  /**
   * 使用StringBuilder简化栈
   * 时间O(n+m) 空间O(n+m)
   */
  public boolean backspaceCompare2(String s, String t) {
    StringBuilder ssb = new StringBuilder(); // 模拟栈
    StringBuilder tsb = new StringBuilder(); // 模拟栈
    // 分别处理两个 String
    for (char c : s.toCharArray()) {
      if (c != '#') {
        ssb.append(c); // 模拟入栈
      } else if (ssb.length() > 0) { // 栈非空才能弹栈
        ssb.deleteCharAt(ssb.length() - 1); // 模拟弹栈
      }
    }
    for (char c : t.toCharArray()) {
      if (c != '#') {
        tsb.append(c); // 模拟入栈
      } else if (tsb.length() > 0) { // 栈非空才能弹栈
        tsb.deleteCharAt(tsb.length() - 1); // 模拟弹栈
      }
    }
    return ssb.toString().equals(tsb.toString());
  }

  /**
   * 双指针
   * 时间O(n+m), 空间O(1)
   */
  public boolean backspaceCompare3(String s, String t) {
    // 退格只会影响前边的字符，不会影响后边的，所以从后向前遍历
    int i = s.length() - 1, j = t.length() - 1;
    // 记录s和t中#的数目
    int skipS = 0, skipT = 0;

    while (i >= 0 || j >= 0) {
      while (i >= 0) {
        if (s.charAt(i) == '#') {// 当前字符为#，#数目+1，下标左移一位
          skipS++;
          i--;
        } else if (skipS > 0) {// 当前字符不为#，但是#数目>0,消耗掉一个#，下标左移一位
          skipS--;
          i--;
        } else {// 不能在移动了
          break;
        }
      }
      while (j >= 0) {
        if (t.charAt(j) == '#') {
          skipT++;
          j--;
        } else if (skipT > 0) {
          skipT--;
          j--;
        } else {
          break;
        }
      }
      if (i >= 0 && j >= 0) {
        if (s.charAt(i) != t.charAt(j)) {
          return false;
        }
      } else {
        if (i >= 0 || j >= 0) {
          return false;
        }
      }
      i--;
      j--;
    }
    return true;
  }

}
