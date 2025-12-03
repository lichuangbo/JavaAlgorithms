package top.xiaotian.algorithms.queue.priority_queue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 295. 数据流的中位数
 * 剑指 Offer 41. 数据流中的中位数
 * <p>
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。 double findMedian() - 返回目前所有元素的中位数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"] [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * <p>
 * 示例 2：
 * <p>
 * 输入： ["MedianFinder","addNum","findMedian","addNum","findMedian"] [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 最多会对 addNum、findMedian 进行 50000 次调用。
 */
public class MedianFinder {
    private Queue<Integer> smallHeap, bigHeap;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        // 大顶堆 存放数据流中较小的那一半数字，由于堆顶元素最大，所以堆顶元素最趋近于全部数据流的中位数
        bigHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 小顶堆 存放数据流中较大的那一半数字，由于堆顶元素最小，所以堆顶元素最趋近于全部数据流的中位数
        smallHeap = new PriorityQueue<>();
        // 重点在于要保证小顶堆的所有元素都大于等于大顶堆的所有元素
    }

    public void addNum(int num) {
        if (bigHeap.size() == smallHeap.size()) {
            // 当数据流是偶数时，加入的元素维护到大顶堆上
            // 流入的数据先放入小顶堆，过滤出小顶堆的最小值，移入大顶堆
            smallHeap.add(num);
            bigHeap.add(smallHeap.poll());
        } else {
            // 当数据流是奇数时，新数据加入后两个堆大小相等
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
