package top.xiaotian.algorithms.stack;

import java.util.Stack;

/**
 * 面试题 03.05. 栈排序
 *
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和
 * isEmpty。当栈为空时，peek 返回 -1。
 *
 * 示例1:
 *
 * 输入： ["SortedStack", "push", "push", "peek", "pop", "peek"] [[], [1], [2], [], [], []] 输出：
 * [null,null,null,1,null,2]
 *
 * @author lichuangbo
 * @date 2022/10/12
 */
class SortedStack {

  // 原始栈
  private final Stack<Integer> stack;
  // 辅助栈
  private final Stack<Integer> tmpStack;

  public SortedStack() {
    stack = new Stack<>();
    tmpStack = new Stack<>();
  }

  public void push(int val) {
    // 比较原始栈和辅助栈顶值，使其满足辅助栈<= val <= 原始栈
    // 原始栈的栈底保证是最大值
    while (!stack.isEmpty() && stack.peek() < val) {
      tmpStack.push(stack.pop());
    }
    while (!tmpStack.isEmpty() && tmpStack.peek() > val) {
      stack.push(tmpStack.pop());
    }
    stack.push(val);//或放到tmpStack中tmpStack.push(val);
  }

  // 懒加载思想，只有在调用pop和peek方法时，才将tmp栈移动到顺序栈里；然后在顺序栈里操作
  // 相较于 每次push都保证原始栈的顺序，懒加载减少了元素在两个栈之间的移动
  public void pop() {
    while (!tmpStack.isEmpty()) {
      stack.push(tmpStack.pop());
    }
    if (!stack.isEmpty()) {
      stack.pop();
    }
  }

  public int peek() {
    while (!tmpStack.isEmpty()) {
      stack.push(tmpStack.pop());
    }
    return stack.isEmpty() ? -1 : stack.peek();
  }

  public boolean isEmpty() {
    return stack.isEmpty() && tmpStack.isEmpty();
  }
}
