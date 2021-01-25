package top.xiaotian.dataStructures.stack.practice;

import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 * 根据 逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time 2021/1/18 14:55
 * @Description: 描述:71
 */
public class EvalRPN {
    /**
     * 时间：数组遍历O(n)
     * 空间：数组入栈O(n)
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (("+-*/").contains(token)) {
                String popB = stack.pop();
                String popA = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(String.valueOf(Integer.parseInt(popA) + Integer.parseInt(popB)));
                        break;
                    case "-":
                        stack.push(String.valueOf(Integer.parseInt(popA) - Integer.parseInt(popB)));
                        break;
                    case "*":
                        stack.push(String.valueOf(Integer.parseInt(popA) * Integer.parseInt(popB)));
                        break;
                    case "/":
                        stack.push(String.valueOf(Integer.parseInt(popA) / Integer.parseInt(popB)));
                        break;
                }
            } else {
                stack.push(token);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}