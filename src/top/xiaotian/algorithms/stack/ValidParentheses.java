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
 * <p>
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "()[]{}"
 * <p>
 * 输出：true
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "(]"
 * <p>
 * 输出：false
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "([])"
 * <p>
 * 输出：true
 * <p>
 * 示例 5：
 * <p>
 * 输入：s = "([)]"
 * <p>
 * 输出：false
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char ch : chars) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (stack.isEmpty()) {
                return false;
            } else {
                char peek = stack.peek();
                if ((peek == '(' && ch == ')') || (peek == '[' && ch == ']') || (peek == '{' && ch == '}')) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
