package top.xiaotian.dataStructures.stack.practice;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 * <p>
 * 实现 MyStack 类：
 * <p>
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 注意：
 * <p>
 * 你只能使用队列的标准操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 * 你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 2, 2, false]
 * <p>
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= x <= 9
 * 最多调用100 次 push、pop、top 和 empty
 * 每次调用 pop 和 top 都保证栈不为空
 */
public class MyStack {

    // queue1作为真实存储元素的队列
    private Queue<Integer> queue1;
    // queue2作为暂存队列，保存top/pop操作的数据
    private Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.offer(x);
    }

    public int pop() {
        // 将除队尾以外的元素挪到暂存队列中
        int size = queue1.size();
        while (size != 1) {
            queue2.offer(queue1.poll());
            size--;
        }
        // 执行出队操作
        Integer item = queue1.poll();
        // 交换队列：保证之后执行入队出队操作的正确性（因为暂存队列保证了队列的先入先出顺序）
        Queue<Integer> tmpQueue = queue1;
        queue1 = queue2;
        queue2 = tmpQueue;
        return item;
    }

    public int top() {
        int size = queue1.size();
        while (size != 1) {
            queue2.offer(queue1.poll());
            size--;
        }
        // top操作不用交换，因为还有一个元素在队列中
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty();
    }
}
