package top.xiaotian.algorithms.queue.priority_queue;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数
 *
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2 输出：[1,2] 或者 [2,1]
 *
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1 输出：[0]
 *
 *
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000 0 <= arr[i] <= 10000
 */
public class LeastNumbers {

  // 大小为k的大顶堆
  public int[] getLeastNumbers(int[] arr, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
    for (int i = 0; i < arr.length; i++) {
      if (pq.size() < k) {
        pq.offer(arr[i]);
      } else if (pq.peek() != null && pq.peek() > arr[i]) {
        pq.poll();
        pq.offer(arr[i]);
      }
    }

    int[] res = new int[k];
    for (int i = 0; i < k; i++) {
      res[i] = pq.poll();
    }
    return res;
  }
}
