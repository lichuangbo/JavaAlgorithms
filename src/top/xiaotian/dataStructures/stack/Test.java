package top.xiaotian.dataStructures.stack;

import java.util.Random;

/**
 * @author lichuangbo
 * @email lichuangbo@smtp.telek.com.cn
 * @time
 * @Description: 描述:
 */
public class Test {
    private static double testStack(Stack<Integer> stack, int opCount) {
        long statTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - statTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 10000000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + " s");

        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        double time2 = testStack(linkedStack, opCount);
        System.out.println("LinkedStack, time: " + time2 + " s");
    }
}
