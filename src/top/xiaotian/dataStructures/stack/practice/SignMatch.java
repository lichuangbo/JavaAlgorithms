package top.xiaotian.dataStructures.stack.practice;

import java.util.Stack;

/**
 * 括号匹配
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class SignMatch {
    public static void main(String[] args) {
        String str1 = "()[]{}";
        String str2 = "{[]}";
        String str3 = "([)]";
        String str4 = ")(";

        SignMatch signMatch = new SignMatch();
        System.out.println(signMatch.isLegal(str1));
        System.out.println(signMatch.isLegal(str2));
        System.out.println(signMatch.isLegal(str3));
        System.out.println(signMatch.isLegal(str4));
    }

    /***
     * 时间: 遍历一遍字符串，O(n)
     * 空间：借助外部栈，O(n)
     * @param str
     * @return
     */
    private boolean isLegal(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if ((c == ')' && stack.peek() == '(') || (c == ']' && stack.peek() == '[') || (c == '}' || stack.peek() == '{')) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
