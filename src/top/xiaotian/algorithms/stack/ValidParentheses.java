package top.xiaotian.algorithms.stack;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 */
public class ValidParentheses {
  public boolean isValid(String s) {
    s = s.trim();
    char[] chars = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < chars.length; i++) {
      char ch = chars[i];
      if (ch == '(' || ch == '[' || ch == '{') {
        stack.push(ch);
      } else if (ch == ')' && !stack.isEmpty() && stack.peek() == '(') {
        stack.pop();
      } else if (ch == ']' && !stack.isEmpty() && stack.peek() == '[') {
        stack.pop();
      } else if (ch == '}' && !stack.isEmpty() && stack.peek() == '{') {
        stack.pop();
      } else {
        return false;
      }
    }
    return stack.isEmpty();
  }
}
