package top.xiaotian.algorithms.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * <p>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 */
public class RemoveDuplicates {
  public String removeDuplicates(String s) {
    char[] chars = s.toCharArray();
    Deque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < chars.length; i++) {
      char ch = chars[i];
      if (!stack.isEmpty() && stack.peek() == ch) {
        stack.pop();
      } else {
        stack.push(ch);
      }
    }

    String res = "";
    while (!stack.isEmpty()) {
      res = stack.pop() + res;
    }
    return res;
  }

  public String removeDuplicates2(String s) {
    // 将 res 当做栈
    StringBuilder res = new StringBuilder();
    // top为 res 的长度
    int top = -1;
    char[] chars = s.toCharArray();
    for (char ch : chars) {
      // 当 top >= 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
      if (top >= 0 && res.charAt(top) == ch) {
        res.deleteCharAt(top);
        top--;
        // 否则，将该字符 入栈，同时top++
      } else {
        res.append(ch);
        top++;
      }
    }
    return res.toString();
  }
}
