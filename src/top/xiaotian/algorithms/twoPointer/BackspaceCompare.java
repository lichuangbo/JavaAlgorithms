package top.xiaotian.algorithms.twoPointer;


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
    /**
     * 栈模拟
     * 假设s字符串长度为m,t字符串长度为n
     * 时间 O(m+n)
     * 空间 O(m+n)
     */
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch != '#')
                stack.push(ch);
            else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        for (char ch : t.toCharArray()) {
            if (ch != '#')
                stack.push(ch);
            else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        StringBuilder sb2 = new StringBuilder();
        while (!stack.isEmpty()) {
            sb2.append(stack.pop());
        }
        // 虽然拼接后的顺序是倒序的，但只要倒序是相等的，那正序肯定是相等的
        return sb.toString().contentEquals(sb2);
    }

    /**
     * 使用StringBuilder简化栈
     * 时间 O(m+n)
     * 空间 O(m+n)
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
        return ssb.toString().contentEquals(tsb);
    }

    /**
     * 双指针
     * 时间 O(m+n)
     * 空间 O(1)
     */
    public boolean backspaceCompare3(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            // 找到 s 的下一个有效字符（这个内循环的目的是如果有#，消除字符，直到i下标指向了有效字符）
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }

            // 找到 t 的下一个有效字符
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

            // 比较字符
            if (i >= 0 && j >= 0) {
                // 短路操作，比如 s = "a#c", t = "b" ，c和b不相等直接返回false
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            } else {
                // 一个存在字符，另一个已处理完，长度不同
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
