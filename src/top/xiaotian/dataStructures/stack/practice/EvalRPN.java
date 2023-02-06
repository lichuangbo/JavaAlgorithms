package top.xiaotian.dataStructures.stack.practice;

import java.util.List;
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
   */
  public int evalRPN(String[] tokens) {
    Stack<Integer> numStack = new Stack<>();
    for (String token : tokens) {
      if (List.of("+", "-", "*", "/").contains(token)) {
        Integer b = numStack.pop();
        Integer a = numStack.pop();
        switch (token) {
          case "+":
            numStack.push(a + b);
            break;
          case "-":
            numStack.push(a - b);
            break;
          case "*":
            numStack.push(a * b);
            break;
          case "/":
            numStack.push(a / b);
            break;
        }
      } else {
        numStack.push(Integer.parseInt(token));
      }
    }
    return numStack.pop();
  }
}
