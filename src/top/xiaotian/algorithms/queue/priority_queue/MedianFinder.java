package top.xiaotian.algorithms.queue.priority_queue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指 Offer 41. 数据流中的中位数
 * 295. 数据流的中位数
 *
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。 double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例 1：
 *
 * 输入： ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"] [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 *
 * 示例 2：
 *
 * 输入： ["MedianFinder","addNum","findMedian","addNum","findMedian"] [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *
 *
 *
 * 限制：
 *
 * 最多会对 addNum、findMedian 进行 50000 次调用。
 */
public class MedianFinder {
  private Queue<Integer> smallHeap, bigHeap;

  /** initialize your data structure here. */
  public MedianFinder() {
    // 大顶堆 堆顶元素最大，存放前半部分数据，堆顶元素最趋近于中位数
    bigHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    // 小顶堆 堆顶元素最小，存放后半部分数据，堆顶元素最趋近于中位数
    smallHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    if (smallHeap.size() == bigHeap.size()) {// 应该插入左半边bigHeap
      if (smallHeap.isEmpty() || num <= smallHeap.peek()) {// 数字比小顶堆堆顶小，不可能出现在右半边，直接插入左半边
        bigHeap.add(num);
      } else {// 数字比堆顶大，说明应该放到右半边；此时右边数目多余左边数目需要调整，将右半边的最小值移动到左半边
        smallHeap.add(num);
        bigHeap.add(smallHeap.poll());
      }
    } else {// 应该插入smallHeap
      if (num >= bigHeap.peek()) {// 数字比大顶堆堆顶大，不可能出现在左半边，直接插入右半边
        smallHeap.add(num);
      } else {
        bigHeap.add(num);
        smallHeap.add(bigHeap.poll());
      }
    }
  }

  public void addNum2(int num) {
    // 为了确保小顶堆存储的是较大的数据，即使两个堆大小相等，新数据应该放大顶堆，也要先加入小顶堆（过一遍），再从小顶堆中拿出最大值放入大顶堆
    if (bigHeap.size() == smallHeap.size()) {
      smallHeap.add(num);
      bigHeap.add(smallHeap.poll());
    } else {
      bigHeap.add(num);
      smallHeap.add(bigHeap.poll());
    }
  }

  public double findMedian() {
    // 维护奇数时左半边比右半边多1；偶数时，左右两边相等
    if (bigHeap.size() != smallHeap.size()) {
      return bigHeap.peek();
    } else {
      return (bigHeap.peek() + smallHeap.peek()) / 2.0;
    }
  }
}
