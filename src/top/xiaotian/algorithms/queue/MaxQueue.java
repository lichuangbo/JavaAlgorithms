package top.xiaotian.algorithms.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 59 - II. 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 * <p>
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 */
public class MaxQueue {

  // 维护一个全局的最大值不可行：如果将最大值删除后，全局的maxVal并没有更新为次大，导致出错

  private final Deque<Integer> queue;
  // 双端队列维护元素大小递减的的顺序，取最大值只需要从递减队列头部取就行
  private final Deque<Integer> deque;

  public MaxQueue() {
    queue = new LinkedList<>();
    deque = new LinkedList<>();
  }

  public int max_value() {
    if (queue.isEmpty()) {
      return -1;
    }
    return deque.peekFirst();
  }

  public void push_back(int value) {
    queue.addLast(value);
    // 当加入的元素小于队尾，需要一直删除队尾，来保证递减顺序
    while (!deque.isEmpty() && deque.peekLast() < value) {
      deque.pollLast();
    }
    // 最后把这个元素加入队尾
    deque.offerLast(value);
  }

  public int pop_front() {
    if (queue.isEmpty()) {
      return -1;
    }
    // 特殊情况：队列头部元素就是最大的，此时出队，需要把递减队列的最大值同步去掉
    if (queue.peek().equals(deque.peekFirst())) {
      deque.pollFirst();
    }
    return queue.removeFirst();
  }
}
