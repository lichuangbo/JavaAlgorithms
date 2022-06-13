package top.xiaotian.algorithms.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 剑指 Offer 41. 数据流中的中位数
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
  // 大顶堆存储较大的那部分数据，小顶堆存储较小的那部分数据，中位数位于大顶堆和小顶堆的堆顶
  // 细化：当总数为偶数时，各自存储n/2；当总数为奇数时，大顶堆存储(n+1)/2,小顶堆存储(n-1)/2
  private Queue<Integer> bigHeap, smallHeap;

  /** initialize your data structure here. */
  public MedianFinder() {
    bigHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    smallHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    // 为了确保大顶堆存储的是较大的数据，即使两个堆大小相等，新数据应该放大顶堆，也要先加入小顶堆（过一遍），再从小顶堆中拿出最大值放入大顶堆
    if (bigHeap.size() == smallHeap.size()) {
      smallHeap.add(num);
      bigHeap.add(smallHeap.poll());
    } else {
      bigHeap.add(num);
      smallHeap.add(bigHeap.poll());
    }
  }

  public double findMedian() {
    if (bigHeap.size() != smallHeap.size()) {
      return bigHeap.peek();
    } else {
      return (bigHeap.peek() + smallHeap.peek()) / 2;
    }
  }
}
