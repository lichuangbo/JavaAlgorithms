package top.xiaotian.dataStructures.stack.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 3+5*8-6=
 *
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class SimpleAdd {
    /***
     *
     **/
    public static void main(String[] args) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operateStack = new Stack<>();
        Map<Character, Integer> weightMap = new HashMap<>();
        weightMap.put('+', 1);
        weightMap.put('-', 1);
        weightMap.put('*', 2);
        weightMap.put('/', 2);
        weightMap.put('=', -1);

//        String str1 = "3+5*8-6=";// 37
        String str1 = "2+9/3*8-3*2=";// 20
        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);
            if (c >= '0' && c <= '9') {
                numStack.push(c - '0');
            } else {
                if (operateStack.isEmpty()) {
                    operateStack.push(c);
                    continue;
                }
                while (!operateStack.isEmpty() && weightMap.get(operateStack.peek()) >= weightMap.get(c)) {
                    Character ch = operateStack.pop();
                    Integer b = numStack.pop();
                    Integer a = numStack.pop();
                    Integer res = 0;
                    switch (ch) {
                        case '+':
                            res = a + b;
                            break;
                        case '-':
                            res = a - b;
                            break;
                        case '*':
                            res = a * b;
                            break;
                        case '/':
                            res = a / b;
                            break;
                    }
                    numStack.push(res);
                }
                operateStack.push(c);
            }
        }
        System.out.println(numStack.pop());
    }
}
