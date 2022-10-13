package top.xiaotian.algorithms.stack.monotone_stack;

import java.util.Stack;

/**
 * 剑指 Offer 30. 包含min函数的栈
 * 面试题 03.02. 栈的最小值
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);   -2
 * minStack.push(0);    -2   peek < 0，不操作
 * minStack.push(-3);   -2 -3  peek > -3, push
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *
 * 提示：
 *
 * 各函数的调用总次数不超过 20000 次
 */
class MinStack {

  private Stack<Integer> stack;
  // minStack维护一个趋势递减的元素顺序
  // 为什么不需要双端队列去做？   push和pop操作都是从栈顶操作的
  private Stack<Integer> minStack;

  /**
   * initialize your data structure here.
   */
  public MinStack() {
    stack = new Stack<>();
    minStack = new Stack();
  }

  public void push(int x) {
    stack.push(x);
    // push 0 1 0 min pop min;  如果是大于的话，重复最小值不会进入minStack（minStack: 0）,pop后0直接出栈，丢失了一个最小值
    if (minStack.isEmpty() || minStack.peek() >= x) {
      minStack.push(x);
    }
  }

  public void pop() {
    int tmp = stack.pop();
    if (!minStack.isEmpty() && minStack.peek() == tmp) {
      minStack.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int min() {
    return minStack.peek();
  }
}
