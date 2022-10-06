package top.xiaotian.algorithms.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 面试题 03.03. 堆盘子
 * 堆盘子。设想有一堆盘子，堆太高可能会倒下来。因此，在现实生活中，盘子堆到一定高度时，我们就会另外堆一堆盘子。请实现数据结构SetOfStacks，模拟这种行为。SetOfStacks应该由多个栈组成，并且在前一个栈填满时新建一个栈。此外，SetOfStacks.push()和SetOfStacks.pop()应该与普通栈的操作方法相同（也就是说，pop()返回的值，应该跟只有一个栈时的情况一样）。 进阶：实现一个popAt(int index)方法，根据指定的子栈，执行pop操作。
 * <p>
 * 当某个栈为空时，应当删除该栈。当栈中没有元素或不存在该栈时，pop，popAt 应返回 -1.
 * <p>
 * 示例1:
 * <p>
 * 输入：
 * ["StackOfPlates", "push", "push", "popAt", "pop", "pop"]
 * [[1], [1], [2], [1], [], []]
 * 输出：
 * [null, null, null, 2, 1, -1]
 */
public class StackOfPlates {
  private List<Stack<Integer>> stackList;
  private int size;

  public StackOfPlates(int cap) {
    stackList = new ArrayList<>();
    size = cap;
  }

  public void push(int val) {
    // 测试用例问题
    if (size <= 0) {
      return;
    }
    // 以下两种情况需要新增一个stack去存放数据
    if (stackList.isEmpty()) {
      stackList.add(new Stack<>());
    }
    if (stackList.get(stackList.size() - 1).size() == size) {
      stackList.add(new Stack<>());
    }
    // 拿到最后的那个栈，放入数据
    Stack<Integer> tmpStack = stackList.get(stackList.size() - 1);
    tmpStack.push(val);
  }

  public int pop() {
    return popAt(stackList.size() - 1);
  }

  public int popAt(int index) {
    if (index == -1 || index >= stackList.size()) {
      return -1;
    }
    Stack<Integer> tmpStack = stackList.get(index);
    int res = tmpStack.pop();
    if (tmpStack.isEmpty()) {
      stackList.remove(index);
    }
    return res;
  }
}
